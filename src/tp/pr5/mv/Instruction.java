package tp.pr5.mv;

import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.Excepciones.MVException;


/**
 * Interfaz encargada de definir la funcionalidad de una instrucción. 
 * Todas las instrucciones deberán implementar esta interfaz. 
 * Los métodos definidos permiten obtener un nuevo objeto instrucción si la cadena 
 * es correcta y ejecutar la instrucción.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public interface Instruction {

	/**
	 * Ejecuta la instrucción sobre la CPU. 
	 * Este método debe dejar la CPU en el mismo estado si se produce un error.
	 * 
	 * @param pila La pila
	 * @param memoria La memoria
	 * @param control La unidad de control
	 * @return Si la ejecución fue correcta
	 */
	public boolean execute(OperandStack pila, Memory memoria, ControlUnit control)throws MVException;
	
	/**
	 * Analiza el texto de entrada y si coincide con la instrucción devuelve un nuevo objeto 
	 * instrucción de ese mismo tipo. 
	 * Devuelve null si el texto no representa a la instrucción
	 * 
	 * @param line Texto representando la instrucción
	 * @return La instrucción correspondiente o null en caso contrario.
	 */
	public Instruction parse(String line);
}
