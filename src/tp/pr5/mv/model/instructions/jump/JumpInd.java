package tp.pr5.mv.model.instructions.jump;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.ControlUnitException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.Excepciones.InsException.JumpException;
import tp.pr5.mv.model.instructions.NoParamOneOperandInstruction;

public class JumpInd extends NoParamOneOperandInstruction {
	
	public JumpInd()
	{		
		super("JUMPIND");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param es el parámetro de la instrucción
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new JumpInd();	
	}
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @return El resultado de ejecutar la operacion
	 */
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int cima) throws InsException
	{
		try {
			control.setCP(cima);
		} catch (ControlUnitException e) {
			throw new JumpException(e);
		}	
		return true;
	}

}
