package tp.pr5.mv.view.Swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;










import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.OperandStack.Data;
import tp.pr5.mv.model.OperandStack.Observer;


/**
 * Clase que representa el panel de pila de nuestra interfaz gráfica
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelPila extends JPanel implements ActionListener, Observer{
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Modelo de lista propio que usaremos para poder interactuar con la pila
	 */
	private ModeloLista list;

	/**
	 * Distintos componentes visuales que forman parte de nuestro panel de pila
	 */
	private JTextField texto;
	private JLabel textoValor;
	private JList <Integer> listaPila;
	private JScrollPane scroll;
	private JPanel panelAux;
	private JButton push;
	private JButton pop;
	
	
	private Controller controller;
	
	/**
	 * Constructor que inicializa los distintos componentes visuales, pone un título al panel
	 * Se encarga además de configurar un layout adecuado, poner activos los botones push y pop
	 * además de añadir todo lo necesario al panel
	 * 
	 * @param controller controlador que se encargará de realizar las acciones de los botones push y pop
	 */
	public PanelPila(Controller controller)
	{
		build();
		
		this.controller = controller;
		
		this.controller.addStackObserver(this);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == push)
		{
			try{
				controller.performPush(Integer.parseInt(texto.getText()));
			}catch (NumberFormatException n){
				JOptionPane.showMessageDialog(this, "Introduzca un número", "Error", JOptionPane.ERROR_MESSAGE);
			} 
		}
		else if(e.getSource() == pop)
		{
			controller.performPop();
		}
		
	}
	
	/**
	 * Clase privada que representan un modelo personalizado para poder interactuar con la lista de la pila
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	private class ModeloLista extends AbstractListModel<Integer>{

		private static final long serialVersionUID = 1L;
		
		private OperandStack.Data data;
 
		
		public ModeloLista()
		{
		}
		
		
		@Override
		public Integer getElementAt(int indice) 
		{
			int l = data.getStack().length -1 ;
			
			return data.getStack()[l- indice];
		}


		@Override
		public int getSize() 
		{
			if(data != null)
			{	
				return data.getStack().length;
			}
			else{
				return 0;
			}
		}
		
		public void onStackChange(Data newData) 
		{
			data = newData;
			
			refresh();
		}
		

		public void refresh()
		{
			fireIntervalAdded(this, 0, data.getStack().length);
		}

	}

	@Override
	public void onStackChange(Data data) 
	{
		SwingUtilities.invokeLater(new ActualizaPila(data));
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar la pila
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaPila implements Runnable
	{
		private OperandStack.Data data;
		
		public ActualizaPila(OperandStack.Data data)
		{
			this.data = data;
		}
		
		@Override
		public void run() 
		{
			list.onStackChange(this.data);
		}
	}
	
	//METODOS PRIVADOS
	
	//Construye el panel con todo lo necesario
	private void build()
	{
		list = new ModeloLista();
		
		listaPila = new JList<Integer>(list);
		scroll = new JScrollPane(listaPila);
		panelAux = new JPanel();
				
		texto = new JTextField();
		textoValor = new JLabel("Valor:");
		push = new JButton("Push");
		pop = new JButton("Pop");
					
		texto.setPreferredSize(new Dimension(100,40));
				
		panelAux.add(textoValor);
		panelAux.add(texto);
		panelAux.add(push);
		panelAux.add(pop);
				
		panelAux.add(new JPanel());
				
		this.setBorder(BorderFactory.createTitledBorder("Pila de operandos"));
		this.setLayout(new BorderLayout());
				
		this.add(scroll, BorderLayout.CENTER);
		this.add(panelAux, BorderLayout.SOUTH);
				
		push.addActionListener(this);
		pop.addActionListener(this);
	}

	

}
