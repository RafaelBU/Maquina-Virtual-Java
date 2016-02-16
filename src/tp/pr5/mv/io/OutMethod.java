package tp.pr5.mv.io;

import java.io.IOException;

/**
 * Interfaz para la salida del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public interface OutMethod {
	
	/**
	 * Escribe un caracter de la salida.
	 */
	public void write(char car) throws IOException;

}
