package tp.pr5.mv.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Clase que sirve para representar la entrada por fichero
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class FileInMethod implements InMethod{
	
private InputStream entrada;
	
	public FileInMethod(InputStream in) 
	{
		entrada = in;
	}
	
	@Override
	public int read() throws IOException 
	{
		return entrada.read();
	}

}
