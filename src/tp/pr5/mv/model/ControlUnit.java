package tp.pr5.mv.model;

import tp.pr5.mv.model.Excepciones.ControlUnitException;

/**
 * Unidad de control de la máquina virtual. 
 * Controla el contador de programa y gestiona el estado de la máquina (halt)
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class ControlUnit extends Observable<ControlUnit.Observer> {

	private int CP;
	private boolean parada;
	private boolean ejecutado;
	private int numInstrucciones;
	
	public interface Observer
	{
		/**
		 * Se invoca cuando ha habido un cambio en la unidad de control
		 */
		void onCPchange(ControlUnit.Data cpData);
		
		/**
		 * Se invoca cuando la ejecución del programa ha terminado de manera
		 * correcta, es decir, cuando se alcanzada una instrucción HALT.
		 */
		public void onHalt();
	}
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 */
	public class Data{

		int contadorPrograma;
		int numInstrucciones;
		
		public Data(int cp, int numIns) 
		{
			contadorPrograma = cp;
			numInstrucciones = numIns;
		}
		
		public int getCp() 
		{
			return contadorPrograma;
		}
		
		public int getNumInstrucciones()
		{
			return numInstrucciones;
		}
		
	}
	
	ControlUnit.Data getData()
	{
		return new Data(this.CP, this.numInstrucciones);
	}
	
	public ControlUnit()
	{
		this.CP = 0;
		this.parada = false;
		this.ejecutado = false;
		this.numInstrucciones = 0;
	}
	
	/**
	 * Devuelve el valor del contador de programa
	 * 
	 * @return el valor del contador de programa
	 */
	public int getCP()
	{
		return this.CP;
	}
	
	/**
	 * Establece el valor del contador de programa. 
	 * Antes de establecer el valor se comprueba que la máquina no esté detenida y que el nuevo valor sea positivo
	 * 
	 * @param newCP es el nuevo valor del contador de programa
	 * @return false si la máquina está detenida o el nuevo valor es negativo. true e.o.c
	 */
	public boolean setCP(int newCP) throws ControlUnitException
	{
		if((newCP < 0) || (isHalted()))
		{
			ejecutado = false;
			throw new ControlUnitException("Has introducido un CP negativo");
		}
		else{
			this.CP = newCP;
			this.numInstrucciones++;
		
			for(Observer o : observers)
			{
				o.onCPchange(getData());
			}
			
			ejecutado = true;
			return true;
		}
	}
	
	/**
	 * Incrementa el valor del contador de programa. 
	 * Antes de establecer el valor se comprueba que la máquina no esté detenida.
	 * 
	 * @param increment es el incremento del cp. Puede ser un valor positivo o negativo.
	 * @return false si la maquina esta detenida
	 */
	public boolean increaseCP(int increment) throws ControlUnitException
	{
		if(isHalted())
		{
			ejecutado = false;
			throw new ControlUnitException("La CPU ya se encuentra parada");
		}
		else{
			this.CP = this.CP + increment;
		
			for(Observer o : observers)
			{
				o.onCPchange(getData());
			}
			
			ejecutado = true;
			return true;
		}
	}
	
	/**
	 * Avanza el valor del contador de programa. 
	 * 
	 * Antes de establecer el valor se comprueba que la máquina no esté detenida. 
	 * También se comprueba que el valor del CP no haya sido modificado 
	 * por los métododos setCP() o increaseCP() por la instruccion ejecutada en ese ciclo.
	 */
	public void next()
	{
		if(!isHalted() && (!ejecutado))
		{
			this.CP++;
			this.numInstrucciones++;
			
			for(Observer o : observers)
			{
				o.onCPchange(getData());
			}
		}
		else{
			this.ejecutado = false;
		}
	}
	
	/**
	 * Para la máquina
	 */
	public void halt()
	{
		this.parada = true;
		
		for(Observer o : observers)
		{
			o.onHalt();
		}
	}
	
	/**
	 * Indica si la máquina está parada
	 * 
	 * @return si la maquina esta parada
	 */
	public boolean isHalted()
	{
		return this.parada;
	}
	
}
