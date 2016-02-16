package tp.pr5.mv.view.Swing;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Clase que representa nuestro panel de salida del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelSalida extends JPanel{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Distintos componentes visuales que forman nuestro panel de salida
	 */
	private JTextArea prueba;
	private JScrollPane scroll;
	
	/**
	 * Atributo que usaremos para ir almacenando la salida que queremos mostrar
	 */
	private String salida;
	
	/**
	 * Constructor que se encarga de inicializar nuestro atributo de salida y las distintas componentes visuales
	 * Se encarga además de darle un título al panel, configurar un layout adecuado y por último añadir lo 
	 * necesario al panel
	 */
	public PanelSalida()
	{	
		build();
	}
	
	
	/**
	 * Método que modifica lo que muestra nuestra salida del programa
	 * 
	 * @param c caracter que queremos añadir a nuestra salida
	 */
	public void produceSalida(char c)
	{
		salida += c;
		prueba.setText(salida);
		
	}
	
	//METODOS PRIVADOS
	
	//Construye el panel con todo lo necesario
	private void build()
	{
		prueba = new JTextArea();
		scroll = new JScrollPane(prueba);
		salida = "";
		
		this.setBorder(BorderFactory.createTitledBorder("Salida del programa"));
		this.setLayout(new GridLayout(1,0));
		
		this.add(scroll);
	}

}
