package tp.pr5.mv.view.Swing;

import java.awt.EventQueue;

import tp.pr5.mv.controller.Controller;


/**
 * Lanzador de la ventana encargada de representar la interfaz grafica de la máquina virtual
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class LauncherVentana {

	public LauncherVentana()
	{}
	
	/**
	 * Genera la ventana y hace que sea visible para el usuario
	 * 
	 * @param cpu cpu encargada de realizar las operaciones que se realizaran en los distintos paneles
	 */
	public static void lanzaVentana(Controller controller){
		final Ventana v = new Ventana(controller);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				v.setVisible(true);
			}
			 });
		}

	}

