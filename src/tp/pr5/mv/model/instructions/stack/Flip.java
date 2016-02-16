package tp.pr5.mv.model.instructions.stack;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.NoParamTwoOperandInstruction;

public class Flip extends NoParamTwoOperandInstruction{

	public Flip()
	{
		super("FLIP");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new Flip();
	}
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @param operand 1 Es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación
	 * @param operand 2 Es el segundo operando (subcima) extraido de la pila por el método execute() y sobre el que se realiza la operación
	 * @return el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int operand1, int operand2)throws InsException
	{
		 try {
			return (pila.push(operand1) && pila.push(operand2));
		} catch (StackException e) {
			throw new InsException(e);
		}
	}
}
