package tp.pr5.mv.view.console.io;

import java.io.IOException;

import tp.pr5.mv.io.InMethod;

/**
 * Clase que sirve para representar la entrada estandar del programa 
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class StdInMethod implements InMethod{
	
	public StdInMethod() 
	{
		
	}

	@Override
	public int read() throws IOException 
	{
		return System.in.read();
	}

}
