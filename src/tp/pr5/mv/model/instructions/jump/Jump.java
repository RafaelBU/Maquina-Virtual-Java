package tp.pr5.mv.model.instructions.jump;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.ControlUnitException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.Excepciones.InsException.JumpException;
import tp.pr5.mv.model.instructions.OneParamNoOperandInstruction;

public class Jump extends OneParamNoOperandInstruction{

	public Jump()
	{		
		this(0);
	}

	public Jump(int parametro)
	{		
		super("JUMP", parametro);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param es el parámetro de la instrucción
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction(int param)
	{
		return new Jump(param);	
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
			return control.setCP(this.param);
		} catch (ControlUnitException e) {
			throw new JumpException("Error en la ejecucion de JUMP " + param + " direccion de salto no valida",e);
		}	
	}
}
