package tp.pr5.mv.model.instructions.logic;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.Excepciones.InsException.LogicException;
import tp.pr5.mv.model.instructions.NoParamOneOperandInstruction;

public class Not extends NoParamOneOperandInstruction{

	public Not()
	{
		super("NOT");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new Not();
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
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int operand)throws InsException
	{
		try{
			if(operand == 0)
			{
				return pila.push(1);
			}
			else{
				return pila.push(0);
			}
		}catch(StackException s){
			throw new LogicException("Error a la hora de ejecutar NOT", s);
		}
		
	}
}