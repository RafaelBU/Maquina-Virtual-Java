package tp.pr5.mv.model.instructions.jump;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.ControlUnitException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.Excepciones.InsException.JumpException;
import tp.pr5.mv.model.instructions.OneParamOneOperandInstruction;

public class BF extends OneParamOneOperandInstruction{
	
	public BF()
	{
		this(0);
	}
	
	public BF (int parametro)
	{	
		super("BF", parametro);
	}

	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param es el parámetro de la instrucción
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction(int param)
	{
		return new BF(param);
	}
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @param operand Es el operando extraido de la pila(cima) sobre el que se realiza la operacion
	 * @return El resultado de ejecutar la operacion
	 */
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int operand) throws InsException
	{
		if(operand == 0)
		{
			try {
				return control.setCP(this.param);
			} catch (ControlUnitException e) {
				throw new JumpException(e);
			}
		}
		else{
			return true;
		}
	}

}
