package tp.pr5.mv.model.instructions.stack;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.NoParamOneOperandInstruction;

public class Dup extends NoParamOneOperandInstruction {

	public Dup()
	{
		super("DUP");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new Dup();
	}
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @param operand Es el operando extraido de la pila(cima) sobre el que se realiza la operacion
	 * @return El resultado de ejecutar la operacion
	 * @throws InsException 
	 */
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int operand) throws InsException
	{
		try{
			pila.push(operand);//Al ser dup necesitamos tener el operando sin desapilar
			
			return pila.push(operand);
		}catch(StackException s){
			throw new InsException(s);
		}
		
	}
}
