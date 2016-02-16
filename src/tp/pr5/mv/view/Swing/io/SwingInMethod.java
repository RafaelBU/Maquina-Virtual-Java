package tp.pr5.mv.view.Swing.io;

import java.io.IOException;

import tp.pr5.mv.io.InMethod;
import tp.pr5.mv.view.Swing.Ventana;

/**
 * Clase que sirve para representar la entrada mediante swing del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class SwingInMethod implements InMethod{
	
	private static Ventana ventana;
	
	private static String buffer = "";
	int idx = 0;
	
	public SwingInMethod(InMethod inMethod) throws IOException {
		
		int c = inMethod.read();
		while (c != -1) {
			buffer += (char) c;
			c = inMethod.read();
		}

	}
	
	/**
	 * Inicializa la ventana
	 * 
	 * @param v ventana de la aplicación 
	 */
	public static void setVentana(Ventana v) 
	{
		ventana = v;
		ventana.muestraEntrada(buffer);
		
	}
	
	@Override
	public int read() throws IOException 
	{
		char c;
		
		if(buffer.length() > idx)
		{
			c = buffer.charAt(idx);
			idx++;
			
			ventana.consumeEntrada(idx -1);
			return (int) c;
		}
		else{
			return -1;
		}
		
	}


}
