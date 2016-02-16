package tp.pr5.mv.view.Swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import tp.pr5.mv.controller.Controller;

/**
 * Clase que representa el panel central de la ventana, que estará compuesto a su vez de un panel norte
 * y un panel sur
 * 
 * @author Rafael Buzón Urbano
 *
 */

public class PanelCentral extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Paneles que forman el panel central
	 */
	private PanelCentralNorte panelNorte;
	private PanelCentralSur panelSur;
	
	
	/**
	 * Constructor que se encarga de inicializar los paneles norte y sur, adaptar el layout del panel
	 * y añadir los paneles al panel central
	 * 
	 * @param cpu cpu que se encargará de realizar las acciones requeridas en el panel norte
	 */
	public PanelCentral(Controller controller)
	{
		build(controller);
		
	}
	
	/**
	 * Método que delega en el panel sur para realizar su acción
	 * 
	 * @param buffer string que necesitamos para poder realizar la acción
	 */
	public void muestraEntrada(String buffer)
	{
		panelSur.muestraEntrada(buffer);
	}
	
	/**
	 * Método que delega en el panel sur para realizar su acción
	 * 
	 * @param indice valor que necesitamos para realizar la acción
	 */
	public void consumeEntrada(int indice)
	{
		panelSur.consumeEntrada(indice);
	}
	
	/**
	 * Método que delega en el panel sur para realizar su acción
	 * 
	 * @param c char que necesitamos para poder realizar la acción
	 */
	public void produceSalida(char c)
	{
		panelSur.produceSalida(c);
	}
	
	//METODOS PRIVADOS
	
	//Construye el panel con los elementos necesarios
	private void build(Controller controller)
	{
		panelNorte = new PanelCentralNorte(controller);
		panelSur = new PanelCentralSur();
		
		this.setLayout(new GridLayout(2,1));
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(panelSur, BorderLayout.SOUTH);
	}

}
