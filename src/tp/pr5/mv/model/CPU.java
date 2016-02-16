package tp.pr5.mv.model;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.Excepciones.MVException;


/**
 * Clase representando la CPU de la máquina virtual. Gestiona los siguientes elementos:
 * Memoria de datos
 * Pila de Operandos
 * Unidad de control
 * Programa con las instrucciones a ejecutar
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class CPU extends Observable<CPU.Observer>{
	
	private Memory memoria;
	private OperandStack pila;
	private ControlUnit control;
	private ProgramMV program;
	private boolean error;
	
	/**
	 * Interfaz implementado por los observadores de la clase
	 * para ser notificados cuando ocurre algún evento de alto nivel 
	 * en la CPU. Para eventos relacionados con la memoria, pila y unidad de control
	 * se deben utilizar los observadores especializados.
	 */
	public interface Observer {
		
		/**
		 * Se invoca cuando se carga un nuevo programa en la CPU. 
		 * Además indica que el contador de programa vale 0 y la memoria
		 * y la pila están vacías.
		 * 
		 * @param program Lista de las instrucciones del nuevo programa.
		 */
		public void onStart(ProgramMV.Data instructions);
		
		/**
		 * Se invoca justo antes de ejecutar una instrucción. 
		 * Si la ejecución de la instrucción tiene exito, a continuación
		 * se invocará a onInstructionEnd. Si la ejecución falla se
		 * invocará a onError. 
		 * 
		 * @param instr Instrucción que se va a ejecutar.
		 */
		public void onInstructionStart(Instruction instr);
		
		/**
		 * Se invoca justo después de ejecutar una instrucción de
		 * manera correcta. 
		 * 
		 * @param instr Instrucción que se completó.
		 * @param mem Estado final de la memoria 
		 * @param ops Estado final de la pila de operandos .
		 * @param pc Estado final del contador de programa.
		 */
		public void onInstructionEnd(Instruction instr,
							Memory.Data mem, OperandStack.Data ops, ControlUnit.Data pc);
		
		/**
		 * Se invoca cuando se produce un error al ejecutar una instrucción.
		 * 
		 * @param instr Instrucción que provocó el error.
		 * @param trapMessage Mensaje con la explicación del error.
		 */
		public void onError(Exception error);
		
	}
	
	
	/**
	 * Método que utilizará el controlador para indicar 
	 * se carga un nuevo programa en la CPU.
	 * Avisa a los observadores enivándoles la lista de instrucciones cargadas
	 */
	
	public void start() 
	{
		for(Observer o : observers)
		{
			o.onStart(program.getData());
		}
	}
	

	/*********************************************************/
	/* Métodos que utilizará el controlador para registrar
	 * la vista como oyente de los elementos internos de la 
	 * cpu: pila, memoria y unidad de control  
	/*********************************************************/


	public void addObserver(OperandStack.Observer obs) 
	{
		pila.addObserver(obs);
	}
	
	public void addObserver(Memory.Observer obs) 
	{
		memoria.addObserver(obs);
	}

	public void addObserver(ControlUnit.Observer obs) 
	{
		control.addObserver(obs);
	}
	
	/**
	 * Constructora por defecto. Crea la memoria y pila con capacidad 100.
	 */
	public CPU()
	{
		this(100,100);
	}

	/**
	 * Constructora. Crea los elementos de la CPU. 
	 * La capacidad máxima de almacenamiento de la memoria y de la pila de operandos se establecerá según los parámetros.
	 * 
	 * @param memory_size Es el tamaño de la memoria
	 * @param stack_size Es el tamaño de la pila
	 */
	public CPU(int memory_size, int stack_size)
	{
		this.pila = new OperandStack(stack_size);
		this.memoria = new Memory(memory_size);
		this.control = new ControlUnit();
		this.program = new ProgramMV();
		this.error = false;
	}
	
	/**
	 * Carga el programa indicado
	 * 
	 * @param programa es el programa que contiene las instrucciones
	 */
	public void loadProgram(ProgramMV programa)
	{
		this.program = programa;	
	}
	
	/**
	 * Ejecuta la instrucción del programa apuntada por el contador de programa. 
	 * No se ejecutará nada si el programa no está cargado o la máquina está parada 
	 * En caso de ejecución correcta de la instrucción se avanza el contador de programa
	 * 
	 * @return false si el programa no está cargado, la máquina está parada 
	 * o hay un error de ejecución de la instrucción. true e.o.c
	 */
	public void step()
	{
		int cp;
		Instruction ins;
		if(!(control.isHalted()))
		{
			if(this.program != null)
			{
				cp = this.control.getCP();
				ins = program.getInstructionAt(cp);
				
				for(Observer o : observers)
				{
					o.onInstructionStart(ins);
				}
			
				try {
					ins.execute(pila, memoria, control);
					for(Observer o : observers)
					{
						o.onInstructionEnd(ins, memoria.getData(), pila.getData(), control.getData());
					}
					
					control.next();//Avanzamos el contador de programa
					
					//Si no hay mas instrucciones almacenadas paramos la CPU
					if(!masInstrucciones(control))
					{
						control.halt();
					}
		
				} catch (MVException e) {
					
					for(Observer o : observers)
					{
						o.onError(e);
					}
					
					//return false;
					error = true;
					
				}
				
				/*for(Observer o : observers)
				{
					o.onInstructionEnd(ins, memoria.getData(), pila.getData(), control.getData());
				}
				
				control.next();//Avanzamos el contador de programa
				
				//Si no hay mas instrucciones almacenadas paramos la CPU
				if(!masInstrucciones(control))
				{
					control.halt();
				}
				
				return true;*/
			}
		}
		
			/*else{
				return false;
			}
		}
		else{
			return false;
		}*/
	}
	
	public void stepAux(Instruction ins)
	{
		try {
			
			for(Observer o : observers)
			{
				o.onInstructionStart(ins);
			}
			
			ins.execute(pila, memoria, control);
			
			for(Observer o : observers)
			{
				o.onInstructionEnd(ins, memoria.getData(), pila.getData(), control.getData());
			}
			
			//return true;
		} catch (MVException e) {
			for(Observer o : observers)
			{
				o.onError(e);
			}
			
			//return false;
		}
	}
	
	public boolean error()
	{
		return this.error;
	}
	
	/**
	 * Método que indica si la CPU ha sido detenida por una instrucción HALT
	 * 
	 * @return Si la CPU está detenida
	 */
	public boolean isHalted()
	{
		return this.control.isHalted();
	}
	
	/**
	 * Devuelve la siguiente instrucción a ejecutar para informar al usuario.
	 * 
	 * @return la siguiente instrucción a ejecuar o null en caso de error.
	 */
	public String getNextInstructionName()
	{
		Instruction ins = this.program.getInstructionAt(control.getCP());
		
		return ins.toString();
		
	}
	
	/**
	 * Método encargado de devolver una representación textual de la CPU 
	 * de acuerdo a las especificaciones del enunciado
	 */
	public String toString()
	{
		return "Memoria: " + memoria.toString() + "\n" + "Pila de operandos: " + pila.toString();
	}
	
	//METODOS PRIVADOS
	
	//Comprueba si hay mas instrucciones que ejecutar y devuelve true si es cierto
	private boolean masInstrucciones(ControlUnit control)
	{
		return (program.getInstructionAt(control.getCP()) != null);
	}
}
