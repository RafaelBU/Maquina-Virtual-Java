package tp.pr5.mv;

import tp.pr5.mv.model.instructions.arithmetic.Add;
import tp.pr5.mv.model.instructions.arithmetic.Div;
import tp.pr5.mv.model.instructions.arithmetic.Mult;
import tp.pr5.mv.model.instructions.arithmetic.Neg;
import tp.pr5.mv.model.instructions.arithmetic.Sub;
import tp.pr5.mv.model.instructions.comparison.EQ;
import tp.pr5.mv.model.instructions.comparison.GT;
import tp.pr5.mv.model.instructions.comparison.LE;
import tp.pr5.mv.model.instructions.comparison.LT;
import tp.pr5.mv.model.instructions.io.In;
import tp.pr5.mv.model.instructions.io.Out;
import tp.pr5.mv.model.instructions.jump.BF;
import tp.pr5.mv.model.instructions.jump.BT;
import tp.pr5.mv.model.instructions.jump.Jump;
import tp.pr5.mv.model.instructions.jump.JumpInd;
import tp.pr5.mv.model.instructions.jump.RBF;
import tp.pr5.mv.model.instructions.jump.RBT;
import tp.pr5.mv.model.instructions.jump.RJump;
import tp.pr5.mv.model.instructions.logic.And;
import tp.pr5.mv.model.instructions.logic.Not;
import tp.pr5.mv.model.instructions.logic.Or;
import tp.pr5.mv.model.instructions.memory.Load;
import tp.pr5.mv.model.instructions.memory.LoadInd;
import tp.pr5.mv.model.instructions.memory.Store;
import tp.pr5.mv.model.instructions.memory.StoreInd;
import tp.pr5.mv.model.instructions.memory.Write;
import tp.pr5.mv.model.instructions.misc.Halt;
import tp.pr5.mv.model.instructions.stack.Dup;
import tp.pr5.mv.model.instructions.stack.Flip;
import tp.pr5.mv.model.instructions.stack.Pop;
import tp.pr5.mv.model.instructions.stack.Push;

/**
 * Clase encargada de analizar la entrada del usuario y generar la instrucción correspondiente
 * Guarda internamente un array con todas las instrucciones disponibles
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class InstructionParser {

	private static Instruction[] arrayInstrucciones = {new Add(), new Div(), new Mult(), new Neg(), 
													   new Sub(), new EQ(), new GT(), new LE(), new LT(), 
													   new Out(), new In(), new BF(), new RBF(), new BT(), new RBT(), new Jump(), 
													   new RJump(), new JumpInd(), new And(), new Not(), new Or(), new Load(), 
													   new LoadInd(), new Store(), new StoreInd(), new Halt(), new Pop(), new Push(), 
													   new Dup(), new Flip(), new Write()};
	                                           
	
	public InstructionParser()
	{
	}
	
	/**
	 * Analiza la orden introducida por el usuario y genera la instrucción correspondiente. 
	 * Devuelve null en caso de error.
	 * 
	 * @param line Es la orden introducida por el usuario
	 * @return la instrucción correspondiente o null en caso de error al analizar la orden en el parámetro line
	 */
	public static Instruction parse(String line)
	{
		for(Instruction i : arrayInstrucciones)//Para cada instruccion del array
		{
			Instruction resultado = i.parse(line);//La parseo a traves de su parse correspondiente
			if(resultado != null)
			{
				return resultado;//Si se ha podido encontrar una instruccion y se ha parseado bien, me salgo del for y devuelvo esa instruccion
			}
		}
		return null;
	}
	
}
