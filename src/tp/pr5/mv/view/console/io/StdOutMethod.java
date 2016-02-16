package tp.pr5.mv.view.console.io;

import java.io.IOException;

import tp.pr5.mv.io.OutMethod;

/**
 * Clase que sirve para representar la salida estandar del programa 
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class StdOutMethod implements OutMethod{

	@Override
	public void write(char car) throws IOException 
	{
		
		System.out.print(car);
	}

}
