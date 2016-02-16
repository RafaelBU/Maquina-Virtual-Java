package tp.pr5.mv.model;

import java.util.Arrays;

import tp.pr5.mv.model.Excepciones.StackException;

/**
 * Clase encargada de representar la pila de operandos.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class OperandStack extends Observable<OperandStack.Observer> {

	private int[] pila; 
	private int cima;
	
	public interface Observer
	{
		/**
		 * Se invoca cuando ha habido un cambio en la pila
		 */
		void onStackChange(OperandStack.Data data);
	}
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 */
	public class Data{
		
		int [] operandos;
		
		public Data(int[] stack)
		{
			operandos = stack;
		}
		
		public int[] getStack()
		{
			return operandos;
		}
		
		public String toString()
		{
			String cadena = "";
			try {
				if(isEmpty())
				{
					cadena = "<vacia>";
				}
				else{
					for(int i = 0; i <= operandos.length-1; i++)
					{
						cadena = cadena + " " + operandos[i];
					}
				}
			} catch (StackException e) {
				cadena = "<vacia>";
			}
			cadena = cadena.trim(); //Elimina espacios en blanco al principio y al final
			return cadena;
		}
		
	}
	
	OperandStack.Data getData()
	{
		return new Data(Arrays.copyOf(pila, cima+1));
	}
	
	
	/**
	 * Constructora que crea la pila de operandos con la capacidad máxima indicada
	 * 
	 * @param max_size es la capacidad máxima de la pila. Se asume que siempre será un número mayor que 0
	 */
	public OperandStack(int max_size)
	{
		this.pila =  new int[max_size];
		this.cima = -1;
	}
	
	/**
	 * Indica si la pila está vacia
	 * 
	 * @return Si la pila está vacia
	 */
	public boolean isEmpty() throws StackException 
	{
		if(cima == -1)
		{
			throw new StackException("La pila esta vacia");
			
		}
		else{
			return false;
		}
	}
	
	/**
	 * Apila un valor
	 * 
	 * @param value Es el valor a apilar
	 * @return true si se ha podidio realizar el apilado 
	 * o false si no se ha realizado porque la pila estaba llena
	 */
	public boolean push(int value) throws StackException
	{
		if(cima == pila.length -1)
		{
			throw new StackException("Error en la ejecucion de PUSH " + value + " la pila esta llena");
		}
		else{
			cima++;
			pila[cima] = value;
		
			for(Observer o : observers)
			{
				o.onStackChange(getData());
			}
			
			return true;
		}
		
	}
	
	/**
	 * Desapila el valor de la cima. 
	 * Los clientes de esta clase deberán comprobar con el método isEmpty() que la pila no esté vacia 
	 * antes de invocar este método. 
	 * Si se intenta eliminar un valor en la pila vacía, el comportamiento es indeterminado.
	 */
	public void pop() throws StackException
	{
		try {
			if(!isEmpty())
			{
				cima--;
			
				for(Observer o : observers)
				{
					o.onStackChange(getData());
				}
			}
		} catch (StackException e) {
			throw new StackException("Error al ejecutar la instruccion POP, la pila ya esta vacia");	
		}	
	}
	
	/**
	 * Devuelve el valor de la cima.
	 * Los clientes de esta clase deberán comprobar con el método isEmpty() que la pila no esté vacia antes de invocar este método. 
	 * En caso de que la pila esté vacia se devolverá 0 por defecto.
	 * 
	 * @return El valor de la cima de la pila o 0 si está vacía
	 */
	public int top() throws StackException
	{	
		int valor = 0;
		try {
			if(isEmpty())
			{
				valor = 0;
			}
			else{
				valor = pila[cima];
			}
		} catch (StackException e) {
			throw new StackException("La pila esta vacia");
		}
		return valor;
	}
	
	/**
	 * Método encargado de devolver una representación textual de la memoria de datos.
	 */
	public String toString()
	{
		String cadena = "";
		try {
			if(isEmpty())
			{
				cadena = "<vacia>";
			}
			else{
				for(int i = 0; i <= cima; i++)
				{
					cadena = cadena + " " + pila[i];
				}
			}
		} catch (StackException e) {
			cadena = "<vacia>";
		}
		cadena = cadena.trim(); //Elimina espacios en blanco al principio y al final
		return cadena;
		
	}
	
}
