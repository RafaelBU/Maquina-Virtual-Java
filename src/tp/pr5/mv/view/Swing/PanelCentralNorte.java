package tp.pr5.mv.view.Swing;

import java.awt.GridLayout;

import javax.swing.JPanel;
import tp.pr5.mv.controller.Controller;

/**
 * Clase que representa el panel central norte, que a su vez contiene otros dos paneles
 * El de memoria y el de la pila
 * 
 * @author Rafael Buzón Urbano
 *
 */

public class PanelCentralNorte extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Distintos paneles que componen el panel central norte
	 */
	private PanelPila panelP;
	private PanelMemoria panelMem;
	
	/**
	 * Constructor que inicializa los paneles de pila y de memoria, configura un layout adecuado
	 * y añade los paneles al panel central norte
	 * 
	 * @param cpu cpu que se encargará de realizar las acciones en los panel de pila y de memoria
	 */
	public PanelCentralNorte(Controller controller)
	{
		build(controller);
		
	}
	
	
	//METODOS PRIVADOS
	
	//Construye el panel con los elementos necesarios
	private void build(Controller controller)
	{
		panelP = new PanelPila(controller);
		panelMem = new PanelMemoria(controller);
		
		this.setLayout(new GridLayout(1,2));
		
		this.add(panelP);
		this.add(panelMem);
	}
}
