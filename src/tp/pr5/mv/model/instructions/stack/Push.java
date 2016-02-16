package tp.pr5.mv.model.instructions.stack;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.OneParamNoOperandInstruction;

public class Push extends OneParamNoOperandInstruction{
		
	public Push()
	{
		this(0);
	}
	
	public Push(int parametro)
	{	
		super("PUSH", parametro);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param es el parámetro de la instrucción
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction(int param)
	{
		return new Push(param);		
	}

	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @return El resultado de ejecutar la operacion
	 */
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control) throws InsException
	{
		try {
			return pila.push(param);
		} catch (StackException sc) {
			throw new InsException(sc);
		}	
	}
}
