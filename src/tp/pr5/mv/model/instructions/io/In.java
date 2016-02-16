package tp.pr5.mv.model.instructions.io;


import java.io.IOException;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.io.InMethod;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.StackException;
import tp.pr5.mv.model.Excepciones.InsException.InsException;
import tp.pr5.mv.model.Excepciones.InsException.IoException;
import tp.pr5.mv.model.instructions.NoParamNoOperandInstruction;

public class In extends NoParamNoOperandInstruction {
	
	private static InMethod entrada;
	
	public static void setEntrada(InMethod in)
	{
		entrada = in;
	}
	
	public In()
	{
		super("IN");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new In();
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
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control)throws InsException
	{
		try{
			int numero = entrada.read();
			if(numero == -1)
			{
				return pila.push(-1);
			}
			else{
				return pila.push(numero);
			}
		}catch(StackException sc){
			throw new IoException(sc);
		}catch(IOException i){
			throw new IoException(i);
		}
		
	}

}
