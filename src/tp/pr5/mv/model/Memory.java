package tp.pr5.mv.model;

import java.util.Arrays;

import tp.pr5.mv.model.Excepciones.MemoryException;

/**
 * Clase encargada de representar la memoria de datos. 
 * Cada dato se define por su dirección y valor. Para representar un dato en memoria se utiliza la clase DataMemoryRegister.
 * 
 * Aunque el rango de direcciones de memoria se supone infinito, 
 * en la práctica, la constructora define la capacidad de la máquina virtual para almacenar datos en esta memoria. 
 * Esto no limita el rando de direcciones a utilizar por el programa, sino el número de datos almacenados.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Memory extends Observable<Memory.Observer>{

	private DataMemoryRegister[] memoria;
	private int contador;
	
	public interface Observer{
		
		/**
		 * Se invoca cuando ha habido un cambio en la memoria
		 */
		void onMemoryChange(Memory.Data data);
	}
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 */
	public class Data{
		
		DataMemoryRegister[] mem;
		
		public Data(DataMemoryRegister[] data) 
		{
			mem = data;
		}

		public DataMemoryRegister[] getData() 
		{
			return mem;
		}
		
		public String toString()
		{
			String cadena = "";
			int i = 0;
			if(mem.length == 0)
			{
				cadena = "<vacia>";
			}
			else{
				while(i < mem.length)
				{
					cadena = cadena + " " + mem[i].toString();
					i++;
				}
			}
			cadena = cadena.trim();
			
			return cadena;
		}
		
	}
	
	Memory.Data getData()
	{
		return new Data(Arrays.copyOf(memoria, contador));
	}
	
	/**
	 * Constructora que crea la memoria de datos 
	 * con la capácidad de almacenamiento máxima indicada en el parámetro
	 * 
	 * @param max_size es la capacidad máxima de la memoria. Se asume que siempre será un número mayor que 0.
	 */
	public Memory(int max_size)
	{
		this.memoria = new DataMemoryRegister[max_size];
		this.contador = 0;
		
	}
	
	/**
	 * Indica que la posición de memoria indicada es correcta y que contiene un valor válido
	 * 
	 * @param pos Es la posición de memoria cuyo valor se desea obtener
	 * @return true si la posición de memoria indicada es correcta y que contiene un valor válido
	 */
	public boolean canLoad(int pos) throws MemoryException
	{
		if(buscaPos(pos)== -1)
		{
			throw new MemoryException("Error al cargar, posicion incorrecta");
		}
		return true;
	}
	
	/**
	 * Devuelve el valor en la posición de memoria indicada. 
	 * En caso de indicarse una posición incorrecta se devolverá 0 por defecto. 
	 * Los clientes de esta clase deben llamar al método canLoad antes de llamar 
	 * a este método para comprobar que la posición de memoria es correcta y que contiene un valor válido.
	 * 
	 * @param pos Es la posición de memoria cuyo valor se desea obtener
	 * @return el valor de dicha posición de memoria si dicha posición es válida, o 0 e.o.c.
	 */
	public int load(int pos) throws MemoryException
	{
		int valor = 0;
		
		canLoad(pos);
		
		int posMemoria = buscaPos(pos);
		valor = this.memoria[posMemoria].getValue();
			
		return valor;
	}
	
	/**
	 * Almacena un valor en la posición de memoria indicada. 
	 * En caso de que esa posición ya almacene un valor se sobreescribirá.
	 * 
	 * @param pos Es la posición de memoria
	 * @param value Es el valor a almacenar
	 * @return Si la operación ha podido realizarse con éxito o si ha fallado 
	 * ya que se ha alcanzado la capacidad máxima o la dirección es incorrecta
	 */
	public boolean store(int pos, int value) throws MemoryException
	{
		
		int posArray, posInsercion;
		if(pos < 0)
		{
			throw new MemoryException("Posicion negativa, se ha intentado almacenar en la pos " + pos);
			//return false;
		}
		
		posArray = buscaPos(pos); //Buscamos a pos en memoria
		
		if(posArray != -1)
		{
			this.memoria[posArray].setValue(value);
			
			for(Observer o : observers)
			{
				o.onMemoryChange(getData());
			}
			
			return true;
		}//Si lo encontramos sobreescribimos el valor con setValue()
		
		if(contador == memoria.length)
		{
			throw new MemoryException("La memoria esta llena");
			
		}//La memoria está llena y no podemos insertar
		
		posInsercion = buscaPosInsercion(pos);
		
		moverRegistros(posInsercion);
		
		this.memoria[posInsercion] = new DataMemoryRegister(pos, value);
		
		for(Observer o : observers)
		{
			o.onMemoryChange(getData());
		}
	
		return true;
		
	}
	
	/**
	 * Método encargado de devolver una representación textual de la memoria de datos.
	 */
	public String toString()
	{
		String cadena = "";
		int i = 0;
		if(contador == 0)
		{
			cadena = "<vacia>";
		}
		else{
			while(i < contador)
			{
				cadena = cadena + " " + memoria[i].toString();
				i++;
			}
		}
		cadena = cadena.trim();
		
		return cadena;
	}
	
	
	//METODOS PRIVADOS
	
	//Mediante el metodo de busqueda binaria, busca si existe la posicion previamente en memoria
	//Si la encuentra la devuelve segun donde este, sino devuelve -1
	private int buscaPos(int pos)
	{
		int ini = 0, fin = contador - 1, mitad, posicion = -1;
		boolean encontrado = false;
		
		while ((ini <= fin) && !encontrado) 
		{
			mitad = (ini + fin) / 2; 
			if (pos == memoria[mitad].getPos())
			{ 
				encontrado = true;
				posicion = mitad;
			}
			else if (pos < memoria[mitad].getPos())
			{ 
				fin = mitad - 1;
			}
			else{ 
				ini = mitad + 1;
			}
		}
		return posicion;
	}
	
	//Hacemos hueco para almacenar el valor nuevo
	private void moverRegistros(int posInsercion)
	{
		for(int j = contador; j > posInsercion; j--)
		{
			memoria[j] = memoria[j-1];
		}
		contador++;
	}
	
	//Buscamos la posicion donde insertar el valor nuevo
	private int buscaPosInsercion(int pos)
	{
		int puntoInsercion = 0;
		boolean encontrado = false;
		
		while((puntoInsercion < contador) && (!encontrado))
		{
			if(memoria[puntoInsercion].getPos() <= pos)
			{
				puntoInsercion++;
				if(memoria[puntoInsercion]!= null)
				{
					if(memoria[puntoInsercion].getPos() >= pos)
					{
						encontrado = true;
					}
				}
				else{
					encontrado = true;
				}
			}
			else{
				return 0;
			}
		}
		
		return puntoInsercion;
	}
}
