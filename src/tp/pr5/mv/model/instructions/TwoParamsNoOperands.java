package tp.pr5.mv.model.instructions;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MVException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;

/**
 * Clase abstracta proporcionando el código común a las instrucciones que
 * tienen dos parámetros y no toman operandos de la pila.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public abstract class TwoParamsNoOperands implements Instruction 
{
	/**
	 * Parametro que indica la pos de memoria
	 */
	protected int pos;
	
	/**
	 * Parametro que indica el valor que queremos almacenar
	 */
	protected int value;
	
	/**
	 * El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario
	 */
	protected String type;
	
	/**
	 * Constructora donde se indica el tipo de la instrucción utilizado al parsear 
	 * o mostrar la instrucción al usuario, el primer parametro y el segundo parametro
	 * 
	 * @param type Es el tipo de la instruccion
	 * @param pos Es la posicion de memoria donde queremos escribir
	 * @param value Es el valor que queremos escribir en memoria
	 */
	protected TwoParamsNoOperands(String type, int pos, int value)
	{
		this.type = type;
		this.pos = pos;
		this.value = value;
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
		
		if((partes.length == 3) && (partes[0].equals(type)))
		{
			pos = Integer.parseInt(partes[1]);
			value = Integer.parseInt(partes[2]);
			return createInstruction(pos, value);
		}
		return null;
	}
	
	/**
	 * Devuelve una representación textual de la instrucción
	 * 
	 */
	public String toString()
	{
		return this.type + " " + this.pos + " " + this.value;
	}
	
	
	//METODOS ABSTRACTOS
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param pos 
	 * @param value
	 * @return el nuevo objeto de tipo Instruction		 
	 * 
	 */
	protected abstract Instruction createInstruction(int pos, int value);
		
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param stack Es la pila
	 * @param memory Es la memoria
	 * @param control Es la unidad de control
	 * @return el resultado de ejecutar la operacion
	 */
	protected abstract boolean operate(OperandStack stack, Memory memory, ControlUnit control) throws InsException;
	
}



