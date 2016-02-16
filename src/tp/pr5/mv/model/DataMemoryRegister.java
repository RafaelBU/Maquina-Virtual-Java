package tp.pr5.mv.model;

/**
 * Clase encargada de representar un valor almacenado en la memoria de datos.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class DataMemoryRegister {

	private int posicion;
	private int valor;
	
	/**
	 * Crea el registro de la memoria de datos con una posición y valor dados
	 * 
	 * @param pos Es la posición del valor en la memoria
	 * @param value Es el valor de dicha posición de memoria
	 */
	public DataMemoryRegister(int pos, int value)
	{
		this.posicion = pos;
		this.valor = value;
	}
	
	/**
	 * Devuelve la posición de memoria ocupada por el registro
	 * 
	 * @return La posición de la memoria ocupada por el registro
	 */
	public int getPos()
	{
		return this.posicion;
	}
	
	/**
	 * Devuelve el valor de dicha posición de memoria
	 * 
	 * @return El valor del registro
	 */
	public int getValue()
	{
		return this.valor;
	}
	
	/**
	 * Establece un nuevo valor en el registro
	 * 
	 * @param nuevo_valor Es el nuevo valor de dicha posición de memoria
	 */
	public void setValue(int nuevo_valor)
	{
		this.valor = nuevo_valor;
	}
	
	/**
	 * Representacion textual de los datos de la clase
	 */
	public String toString()
	{
		String cadena = "[" + getPos() + "]" + ":" + getValue();
		cadena =  cadena.trim();
		
		return cadena;
	}
}