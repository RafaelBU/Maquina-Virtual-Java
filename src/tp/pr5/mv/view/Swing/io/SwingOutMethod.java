package tp.pr5.mv.view.Swing.io;

import java.io.IOException;

import tp.pr5.mv.io.OutMethod;
import tp.pr5.mv.view.Swing.Ventana;

/**
 * Clase que sirve para representar la salida mediante swing del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class SwingOutMethod implements OutMethod{

	private OutMethod outMethod;
	private static Ventana ventana;
	private boolean fichero;

	public SwingOutMethod(OutMethod outMethod, boolean fichero) 
	{
		this.outMethod = outMethod;
		this.fichero = fichero;
	}

	@Override
	public void write(char c) throws IOException 
	{
		if(ventana != null)
		{
			ventana.produceSalida(c);
		}
		
		if(fichero)
		{
			outMethod.write(c);
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
	}
}
