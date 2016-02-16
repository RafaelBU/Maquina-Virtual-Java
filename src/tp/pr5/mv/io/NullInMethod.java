package tp.pr5.mv.io;

/**
 * Clase que sirve para representar la entrada "nula" del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class NullInMethod implements InMethod{
	
	public NullInMethod() 
	{
		
	}
	
	@Override
	public int read() 
	{
		return 0;
	}

}
