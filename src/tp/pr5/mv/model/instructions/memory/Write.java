package tp.pr5.mv.model.instructions.memory;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MemoryException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.TwoParamsNoOperands;

public class Write extends TwoParamsNoOperands{
	
	public Write()
	{
		this(0, 0);
	}
	
	public Write(int pos, int value)
	{
		super("WRITE", pos, value);
	}
	

	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param pos Es la posicion de memoria donde queremos escribir
	 * @param value Es el valor que queremos escribir en memoria
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction(int pos, int value)
	{	
		return new Write(pos, value);
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
			return memoria.store(this.pos, this.value);
		} catch (MemoryException me) {
			throw new InsException("Se ha producido un error en la instruccion WRITE", me);
		}
	}

}
