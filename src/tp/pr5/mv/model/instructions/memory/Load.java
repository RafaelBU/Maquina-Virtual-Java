package tp.pr5.mv.model.instructions.memory;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MemoryException;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.instructions.OneParamNoOperandInstruction;

public class Load extends OneParamNoOperandInstruction{

	public Load()
	{
		this(0);
	}
	
	public Load(int param)
	{
		super("LOAD", param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @param es el parámetro de la instrucción
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction(int param)
	{
		return new Load(param);	
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
		int valor = 0;
		
		try {
			memoria.canLoad(this.param);
			valor = memoria.load(this.param);
			return pila.push(valor);
		} catch (MemoryException m) {
			throw new InsException("Error al ejecutar LOAD " + param + " posicion no valida");
		}catch(StackException s){
			throw new InsException(s);
		}
	}
		
}
