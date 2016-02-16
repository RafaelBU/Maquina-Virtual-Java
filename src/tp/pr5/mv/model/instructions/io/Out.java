package tp.pr5.mv.model.instructions.io;

import java.io.IOException;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.io.OutMethod;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.instructions.NoParamOneOperandInstruction;


public class Out extends NoParamOneOperandInstruction {

	private static OutMethod salida;
	
	public Out()
	{
		super("OUT");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente.
	 * 
	 * @return el nuevo objeto de tipo Instruction
	 */
	protected Instruction createInstruction()
	{
		return new Out();
	}
	
	public static void setSalida(OutMethod o)
	{
		salida = o;
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
	protected boolean operate(OperandStack pila, Memory memoria, ControlUnit control, int operand)
	{
		char caracter;
		caracter = (char)operand;
		try {
			salida.write(caracter);
			return true;
		} catch (IOException i) {
			return false;
		}
	
		
	}
}
