package tp.pr5.mv.view.Swing;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Clase que representa nuestro panel de entrada del programa
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelEntrada extends JPanel{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Diferentes componentes visuales que necesitamos para poder representar el panel de entrada
	 */
	private JTextArea areaEntrada;
	private JScrollPane scroll;
	
	/**
	 * Atributo que contendra el string que queremos mostrar por la entrada del programa
	 */
	private String buffer;
	
	/**
	 * Constructor que se encarga de inicializar los distintos componentes visuales del panel
	 * Le pone un titulo al panel, configura un layout adecuado y añade los componentes al panel
	 */
	public PanelEntrada()
	{
		build();
	}
	
	/**
	 * Modifica el area de entrada de nuestro programa para que muestre lo que contiene nuestro 
	 * string buffer
	 * 
	 * @param buffer String que contiene el texto que queremos mostrar por la entrada del programa
	 */
	public void muestraEntrada(String buffer)
	{
		this.buffer = buffer;
		areaEntrada.setText(buffer);
	}
	
	/**
	 * Modifica la entrada cambiando un caracter por un *
	 * 
	 * @param indice indica que caracter de la entrada vamos a modificar por el *
	 */
	public void consumeEntrada(int indice)
	{
		char [] caracteres = buffer.toCharArray();
		caracteres[indice] = '*';
		buffer = "";
		
		for(int i = 0; i < caracteres.length; i++)
		{
			buffer += caracteres[i];
		}
		
		areaEntrada.setText(buffer);
	}
	
	//METODOS PRIVADOS
	
	//Construye el panel con los elementos necesarios
	private void build()
	{
		areaEntrada = new JTextArea();
		scroll = new JScrollPane(areaEntrada);
		
		this.setBorder(BorderFactory.createTitledBorder("Entrada del programa"));
		this.setLayout(new GridLayout(1,0));
		this.add(scroll);
	}

}
