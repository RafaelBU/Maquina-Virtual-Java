package tp.pr5.mv.io;

import java.io.IOException;

/**
 * Clase que sirve para representar la salida "nula" del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class NullOutMethod implements OutMethod{

	@Override
	public void write(char car) throws IOException 
	{
		//No produce efecto
		
	}

}
