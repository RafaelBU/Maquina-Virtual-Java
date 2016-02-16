package tp.pr5.mv.view.console;

import java.util.Arrays;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.InstructionParser;
import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.model.CPU.Observer;
import tp.pr5.mv.model.ProgramMV.Data;

/**
 * Clase encargada de las acciones que van relacionadas con la vista de consola
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Console implements Observer{
	
	private static final String MSG_INTRO = "Introduce el programa fuente";
	private static final String MSG_SHOW = "El programa introducido es:";
	private static final String MSG_PROMPT = "> ";
	private static final String END_TOKEN = "END";
	private static final String MSG_EXEC_BEGIN = "Comienza la ejecucion de ";
	private static final String MSG_EXEC_END = "El estado de la maquina tras ejecutar la instruccion es:";
	private Instruction [] contenedor;
	private int contador;
	
	private boolean batch;
	
	private Controller controller;
	
	/**
	 * Constructor
	 * 
	 * @param con controlador
	 */
	public Console(Controller con)
	{
		contenedor = new Instruction[100];
		contador = 0;
		this.controller = con;
		
	}
	
	/**
	 * Sirve para cambiar el boolean que indica el modo de ejecución
	 * 
	 * @param modo indica el modo
	 */
	public void setBatch(boolean modo)
	{
		this.batch = modo;
	}
	
	public Instruction[] getContenedor()
	{
		return contenedor;
	}
	
	public int getContador()
	{
		return contador;
	}
	
	/**
	 * Método encargado de leer el programa, mostrando al usuario el prompt (>) y los mensajes de información o error. 
	 * Las instrucciones leidas se analizan con el InstructionParser. 
	 * Termina cuando el usuario introduce el valor definido por la constante END_TOKEN. 
	 * Al terminar de leer se muestra el programa cargado.
	 * 
	 * @param input de entrada desde donde se lee el programa. Este método no se encarga de cerrar el scanner.
	 */
	public void readProgram(java.util.Scanner input, boolean batch)
	{
		this.batch = batch;
		pideInstrucciones(input, batch);
	}
	
	
	//METODOS PRIVADOS
	
	//Pide las intrucciones al usuario hasta que teclea la palabra "END"
	private void pideInstrucciones(java.util.Scanner input, boolean batch)
	{
		String cadena; 
		if(!batch)
		{
			System.out.println(MSG_INTRO);
			System.out.print(MSG_PROMPT); 
		}
		cadena = input.nextLine();
		
		if(batch) //Si estamos en modo batch no nos hace falta un end, sino un final de entrada
		{
			contenedor[contador] = InstructionParser.parse(cadena);
			if(contenedor[contador] == null)
			{
				System.err.println("Error en el programa. Linea " + cadena );
				System.exit(2);
			}
			else{
				contador++;
				
			}
			
			while(input.hasNext())
			{
				cadena = input.nextLine();
				contenedor[contador] = InstructionParser.parse(cadena);
				if(contenedor[contador] == null)
				{
					System.err.println("Error en el programa. Linea " + cadena );
					System.exit(2);
				}
				else{
					contador++;
					
				}
				
			}
		}
		else //Si estamos en modo interactivo necesitamos "end" para no meter mas instrucciones
		{
			while(!(cadena.equalsIgnoreCase(END_TOKEN)))
			{
				contenedor[contador] = InstructionParser.parse(cadena);
				if(contenedor[contador] == null)
				{
					System.err.println("Error en el programa. Linea " + cadena );
				}
				else{
					contador++;
					
				}
				
				System.out.print(MSG_PROMPT);
				
				cadena = input.nextLine();
			}
		}
		
	}
	
	//Muestra las instrucciones introducidas
	private void muestraInstrucciones()
	{
		int cont = 0;
		System.out.println(MSG_SHOW);
		while(this.contenedor[cont] != null)
		{
			System.out.println(cont + ": " + this.contenedor[cont].toString());
			cont++;
		}
	}

	@Override
	public void onStart(Data instructions) 
	{
		this.contenedor = Arrays.copyOf(instructions.getInstructions(), this.contenedor.length);
		if(!this.batch)
		{
			muestraInstrucciones();
		}
	}

	@Override
	public void onInstructionStart(Instruction instr) 
	{
		if(!this.batch)
		{
			System.out.println(MSG_EXEC_BEGIN + instr.toString());
		}
	}

	@Override
	public void onInstructionEnd(Instruction instr, tp.pr5.mv.model.Memory.Data mem,
			tp.pr5.mv.model.OperandStack.Data ops, tp.pr5.mv.model.ControlUnit.Data pc) 
	{
		if(!this.batch)
		{
			System.out.println(MSG_EXEC_END);
			System.out.println("Memoria: " + mem.toString()); 
			System.out.println("Pila de operandos: " + ops.toString());
		}
	}

	@Override
	public void onError(Exception error) 
	{
		error.printStackTrace();
		if(this.batch)
		{
			System.exit(2);
		}
		
	}

}
