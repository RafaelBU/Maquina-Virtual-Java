package tp.pr5.mv.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Clase que sirve para representar la salida por fichero
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class FileOutMethod implements OutMethod {

	private OutputStream salida;
	
	public FileOutMethod(OutputStream out) {
		salida = out;
	}
	
	
	@Override
	public void write(char car) throws IOException 
	{
		salida.write(car);
	}

}
