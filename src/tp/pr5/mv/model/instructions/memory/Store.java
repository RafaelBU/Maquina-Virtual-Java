package tp.pr5.mv.model.instructions.memory;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MemoryException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.OneParamOneOperandInstruction;

public class Store extends OneParamOneOperandInstruction{
	
	public Store()
	{
		this(0);
	}
	
	public Store(int parametro)
	{
		super("STORE", parametro);
	}

	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param es el parámetro de la instrucción
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction(int param)
	{	
		return new Store(param);
	}
	
	/**
	 * Realiza la operación correspondiente.
	 * 
	 * @param pila Es la pila
	 * @param memoria Es la memoria
	 * @param control Es la unidad de control
	 * @param operando Es el operando extraido de la pila(cima) sobre el que se realiza la operacion
	 * @return El resultado de ejecutar la operacion
	 */
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int operando) throws InsException
	{
		try {
			return memoria.store(this.param, operando);
		} catch (MemoryException me) {
			throw new InsException("Se ha producido un error en la instruccion STORE", me);
		}
	}
}
