package tp.pr5.mv.model.instructions.misc;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.instructions.NoParamNoOperandInstruction;

public class Halt extends NoParamNoOperandInstruction{

	public Halt()
	{
		super("HALT");
	}
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param stack Es la pila
	 * @param memory Es la memoria
	 * @param control Es la unidad de control
	 * @return el resultado de ejecutar la operacion
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control)
	{
		control.halt();
		return true;
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new Halt();
	}
	
}
