package tp.pr5.mv.model.instructions;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MVException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;

/**
 * Clase abstracta proporcionando el código común a las instrucciones que 
 * tienen un parámetro y no toman ningún operando de la pila.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public abstract class OneParamNoOperandInstruction implements Instruction {

	/**
	 * El parametro de la instruccion
	 */
	protected int param;
	
	/**
	 * El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario
	 */
	protected String type;
	
	/**
	 * Constructora donde se indica el tipo de la instrucción utilizado al parsear 
	 * o mostrar la instrucción al usuario y el valor del parámetro
	 * 
	 * @param tipo Es el tipo de la instruccion
	 * @param parametro Es el parametro de la instruccion
	 */
	protected OneParamNoOperandInstruction(String tipo, int parametro)
	{
		this.type = tipo;
		this.param = parametro;
		
	}
	
	/**
	 * Ejecuta la instrucción sobre la CPU. 
	 * Este método debe dejar la CPU en el mismo estado si se produce un error.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @return Si la ejecucion fue correcta
	 */
	public boolean execute(OperandStack pila, Memory memoria, ControlUnit control) throws MVException
	{
		
		return operate(pila, memoria, control);
		
	}
	
	/**
	 * Analiza el texto de entrada y si coincide con la instrucción devuelve un nuevo objeto instrucción de ese mismo tipo. 
	 * Devuelve null si el texto no representa a la instrucción
	 * 
	 * @param line Texto representando la instruccion
	 * @return la instrucción correspondiente o null en caso contrario.
	 */
	public Instruction parse(String line)
	{
		line = line.toUpperCase().trim();
		String[]partes = line.split("\\s+"); 
		
		if((partes.length == 2) && (partes[0].equals(type))) 
		{
			param = Integer.parseInt(partes[1]);
			return createInstruction(param);
		}
		return null;
	}
	
	/**
	 * Devuelve una representación textual de la instrucción
	 * 
	 */
	public String toString()
	{
		return this.type + " " + this.param;
	}
	
	//METODOS ABSTRACTOS
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param param Es el parametro de la instruccion
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected abstract Instruction createInstruction(int param); 
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @return el resultado de ejecutar la operación.
	 */
	protected abstract boolean operate(OperandStack pila, Memory memoria, ControlUnit control) throws InsException; 
	
}
