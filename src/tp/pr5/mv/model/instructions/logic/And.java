package tp.pr5.mv.model.instructions.logic;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.Excepciones.InsException.LogicException;
import tp.pr5.mv.model.instructions.NoParamTwoOperandInstruction;


public class And extends NoParamTwoOperandInstruction{
	
	public And()
	{
		super("AND");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new And();
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
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int operand1,int operand2)throws InsException
	{
		boolean cimaBin, subcimaBin;
		cimaBin = asignaCimaBin(operand1);
		subcimaBin = asignaSubcimaBin(operand2);
		
		if(cimaBin && subcimaBin)
		{
			try {
				return pila.push(1);
			} catch (StackException sc) {
				throw new LogicException("Se ha producido algun error en la instruccion AND", sc);
			}
		}
		else{
			try {
				return pila.push(0);
			} catch (StackException sc) {
				throw new LogicException("Se ha producido algun error en la instruccion AND", sc);
			}
		}
	}
	
	
	//METODOS PRIVADOS
	
	//Devuelve false si la cima es un cero y true para cualquier otro valor
	private boolean asignaCimaBin(int cima)
	{	
		if(cima == 0)
		{
			return false;
		}
		else{
			return true;
		}
		
	}
	
	//Devuelve false si la subcima es cero y true para cualquier otro valor
	private boolean asignaSubcimaBin(int subcima)
	{	
		if(subcima == 0)
		{
			return false;
		}
		else{
			return true;
		}
		
	}
}
