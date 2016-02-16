package tp.pr5.mv.model.instructions.comparison;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.CompareException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.NoParamTwoOperandInstruction;

public class LT extends NoParamTwoOperandInstruction{

	public LT()
	{
		super("LT");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new LT();
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
		try{
			if(operand1 > operand2)
			{
				return pila.push(1);
			}
			else{
				return pila.push(0);
			}
		}catch(StackException s){
			throw new CompareException("Error en la ejecucion de LT", s);
		}
		
	}
}
