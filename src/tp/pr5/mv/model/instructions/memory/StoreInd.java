package tp.pr5.mv.model.instructions.memory;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MemoryException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.NoParamTwoOperandInstruction;


public class StoreInd extends NoParamTwoOperandInstruction {

	public StoreInd()
	{
		super("STOREIND");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new StoreInd();
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
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int cima, int subcima)throws InsException
	{
		try {
			return memoria.store(subcima, cima);
		} catch (MemoryException me) {
			throw new InsException("Se ha producido un error en la instruccion STOREIND", me);
		}
	}
}
