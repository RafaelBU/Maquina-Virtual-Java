package tp.pr5.mv.view.Swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Clase que representa el panel central sur, que a su vez contiene otros dos paneles
 * El de entrada y el de salida del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelCentralSur extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Paneles que componen el panel central sur
	 */
	private PanelEntrada in;
	private PanelSalida out;
	
	/**
	 * Constructor que inicializa los paneles de entrada y de salida, configura un layout adecuado
	 * y añade los paneles al panel central sur
	 */
	public PanelCentralSur()
	{
		build();
	}
	
	/**
	 * Método que delega en el panel de entrada para realizar su acción
	 * 
	 * @param buffer string que necesitamos para poder realizar la acción
	 */
	public void muestraEntrada(String buffer)
	{
		in.muestraEntrada(buffer);
	}
	
	/**
	 * Método que delega en el panel de entrada para realizar su acción
	 * 
	 * @param indice valor que necesitamos para realizar la acción
	 */
	public void consumeEntrada(int indice)
	{
		in.consumeEntrada(indice);
	}
	
	/**
	 * Método que delega en el panel de salida para realizar su acción
	 * 
	 * @param c char que necesitamos para poder realizar la acción
	 */
	public void produceSalida(char c)
	{
		out.produceSalida(c);
	}
	
	//METODOS PRIVADOS
	
	//Construye el panel con los elementos necesarios
	private void build()
	{
		in = new PanelEntrada();
		out = new PanelSalida();
		
		this.setLayout(new GridLayout(2,1));
		
		this.add(in, BorderLayout.NORTH);
		this.add(out, BorderLayout.SOUTH);
	}

}
