package tp.pr5.mv.model;

import java.util.Arrays;

import tp.pr5.mv.Instruction;


/**
 * Clase encargada de leer el programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class ProgramMV {

	private Instruction [] contenedor;
	private int contador;
	
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 */
	public class Data{
		
		Instruction [] instrucciones;
		
		public Data(Instruction[] instructions) 
		{
			instrucciones = instructions;
		}
		
		public Instruction[] getInstructions()
		{
			return instrucciones;
		}
	}
	
	ProgramMV.Data getData()
	{
		return new Data(Arrays.copyOf(contenedor, contador));
	}
	
	public ProgramMV()
	{
		this.contenedor = new Instruction[100];
		this.contador = 0;
	}
	
	public ProgramMV(Instruction[] contenedor, int contador)
	{
		this.contenedor = contenedor;
		this.contador = contador;
	}
	
	
	/**
	 * Devuelve la instruccion en la posicion indicada
	 * 
	 * @param i Es la posicion de la instruccion
	 * @return la instrucción si la posición era correcta (0 <= i < numInstrucciones)
	 */
	public Instruction getInstructionAt(int i)
	{
		if(0 <= i)
		{
			if( i < this.contador)
			{
				return contenedor[i];
			}
			else{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
}