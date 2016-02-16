package tp.pr5.mv.model.instructions;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MVException;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;

/**
 * Clase abstracta proporcionando el código común a las instrucciones 
 * que no tienen parámetro y toman un operando de la pila.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public abstract class NoParamOneOperandInstruction implements Instruction{

	/**
	 * El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario
	 */
	protected String type;
	
	protected NoParamOneOperandInstruction(String tipo)
	{
		this.type = tipo;
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
		int cima = 0;
		
		try {
			pila.isEmpty();
			
			cima = pila.top();
			pila.pop();
			
			operate(pila, memoria, control, cima);
			return true;
			
		} catch (StackException e) {
			throw new StackException("Error ejecutando " + type + " ,faltan operandos en la pila");
		} catch (InsException i){
			pila.push(cima);
			throw new InsException(i);
		}
		
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
		
		if(partes[0].equals(type))
		{
			if(partes.length == 1)
			{
				return createInstruction();
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}
	
	/**
	 * Devuelve una representación textual de la instrucción
	 * 
	 */
	public String toString()
	{
		return this.type;
	}
	
	
	//METODOS ABSTRACTOS
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected abstract Instruction createInstruction();
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param stack Es la pila
	 * @param memory Es la memoria
	 * @param control Es la unidad de control
	 * @param operand es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación
	 * @return El resultado de ejecutar la operacion
	 */
	protected abstract boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand)throws InsException;
}
