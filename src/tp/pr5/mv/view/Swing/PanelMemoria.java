package tp.pr5.mv.view.Swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.Memory.Data;
import tp.pr5.mv.model.Memory.Observer;

/**
 * Clase que representa el panel de memoria de nuestra interfaz
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelMemoria extends JPanel implements ActionListener, Observer{

	private static final long serialVersionUID = 1L;

	/**
	 * Modelo personalizado que vamos a usar para poder trabajar con la tabla que represente la memoria
	 */
	private ModeloTablaMemoria modeloTabla;


	/**
	 * Distintos componentes visuales que forman nuestro panel de memoria
	 */
	private JButton write;
	private JLabel letreroPos;
	private JLabel letreroValue; 
	private JTextField textoPos;
	private JTextField textoValue;
	private JTable tabla;
	private JScrollPane scroll;
	private JPanel panelAux;
	
	private Controller controller;
	
	/**
	 * Constructor que inicializa el modelo y los distintos componentes visuales del panel
	 * También se encarga de darle un título al panel, configurar un layout adecuado, activar el boton write
	 * y añadir los paneles y componentes necesarios al panel de memoria
	 * 
	 * @param controller controlador que se encargara de hacer las acciones del boton write
	 */
	public PanelMemoria (Controller controller)
	{
		build();
		
		this.controller = controller;
		this.controller.addMemoryObserver(this);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int pos;
		int value;
		try {
			pos = Integer.parseInt(textoPos.getText());
			value = Integer.parseInt(textoValue.getText());
			controller.performWrite(pos, value);
		} catch(NumberFormatException num){
			JOptionPane.showMessageDialog(this, "Introduzca un número", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	/**
	 * Clase privada que representan un modelo personalizado para poder interactuar con la tabla de memoria
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	private class ModeloTablaMemoria extends AbstractTableModel
	{
		private static final long serialVersionUID = 1L;
		
		String [] nombreColumn = {"POSICION", "VALOR"};
		private Memory.Data data;
		
		/**
		 * Constructor
		 */
		public ModeloTablaMemoria()
		{
		}
		
		
		@Override
		public String getColumnName(int column) 
		{
			return nombreColumn[column];
		}
		
		@Override
		public int getColumnCount() 
		{
			return 2;
		}

		@Override
		public int getRowCount() 
		{
			if(data != null)
			{
				return data.getData().length;
			}
			else{
				return 0;
			}
		}

		@Override
		public Object getValueAt(int fila, int columna) 
		{
			if(data != null)
			{
				if (columna == 0)
				{
					return data.getData()[fila].getPos();
				}	
				else{
					return data.getData()[fila].getValue();
				}
			}
			else{
				return 0;
			}
			
		}
		
		public void onMemoryChange(Data newData)
		{
			data = newData;
		
			refrescaVentana();
		}
		
		/**
		 * Metodo que se encarga de actualizar la parte gráfica
		 */
		private void refrescaVentana()
		{
			fireTableDataChanged();
		}
		
	}


	@Override
	public void onMemoryChange(Data data) 
	{
		SwingUtilities.invokeLater(new ActualizaMemoria(data));
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar la memoria
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaMemoria implements Runnable
	{
		private Memory.Data dataMem;
		
		public ActualizaMemoria(Memory.Data data)
		{
			this.dataMem = data;
		}
		
		@Override
		public void run() 
		{
			modeloTabla.onMemoryChange(this.dataMem);
			
		}
		
	}
	
	//METODOS PRIVADOS
	
	//Construye el panel con los elementos necesarios
	private void build()
	{
		modeloTabla = new ModeloTablaMemoria();
		tabla = new JTable(modeloTabla);
		
		scroll = new JScrollPane(tabla);
		panelAux = new JPanel();
		textoPos = new JTextField();
		textoValue = new JTextField();
		letreroPos = new JLabel("Pos:");
		letreroValue = new JLabel("Value:");
		write = new JButton("Write");
		
		textoPos.setPreferredSize(new Dimension(100,40));
		textoValue.setPreferredSize(new Dimension(100,40));
		
		this.setBorder(BorderFactory.createTitledBorder("Memoria de la maquina"));
		this.setLayout(new BorderLayout());
		
		
		panelAux.add(letreroPos);
		panelAux.add(textoPos);
		panelAux.add(letreroValue);
		panelAux.add(textoValue);
		panelAux.add(write);
		
		write.addActionListener(this);

		
		this.add(scroll, BorderLayout.CENTER);
		this.add(panelAux, BorderLayout.SOUTH);
	}
	
}
