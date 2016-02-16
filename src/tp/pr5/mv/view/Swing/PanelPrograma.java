package tp.pr5.mv.view.Swing;


import java.awt.Dimension;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.ProgramMV;
import tp.pr5.mv.model.CPU.Observer;
import tp.pr5.mv.model.ControlUnit.Data;

/**
 * Clase que representa el panel donde se muestra el programa que vamos a ejecutar
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelPrograma extends JPanel implements ControlUnit.Observer, Observer{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Modelo personalizado que utilizaremos para interactuar con nuestro panel de programa
	 */
	private ModeloListaPrograma modeloLista;
	
	/**
	 * Distintos componentes visuales que forman nuestro panel de programa
	 */
	private JList <String> listaPrograma;
	private JScrollPane scroll;
	
	/**
	 * Constructor que inicializa el modelo y los distintos componentes visuales
	 * Le asigna un título al panel, configura tamaños y por último añade lo necesario al panel
	 */
	public PanelPrograma(Controller controller)
	{
		build();
		
		controller.addCPUObserver(this);
		controller.addControlUnitObserver(this);
		
	}
	
	
	/**
	 * Clase privada que representan un modelo personalizado para poder interactuar con el panel de programa
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	private static class ModeloListaPrograma extends AbstractListModel<String> {

		private static final long serialVersionUID = 1L;
		
		private int cp;
		private ProgramMV.Data data;

		/**
		 * Constructor que inicializa el contador de programa a 0
		 */
		public ModeloListaPrograma() 
		{
			cp = 0;
		}
		
		public void onStart(ProgramMV.Data newData)
		{
			this.data = newData;
			refrescar() ;
		}
		

		@Override
		public int getSize() 
		{
			if (data != null)
			{
				return data.getInstructions().length;
			}
			else{
				return 0;
			}
		}

		@Override
		public String getElementAt(int index) 
		{
			String instr = index + ":" + "  " + data.getInstructions()[index].toString();
			if (index == cp)
			{
				instr = "*" + " "+ instr;
			}
			return instr;
		}
		
		
		public void onCPchange(Data cpData)
		{
			cp = cpData.getCp();
			refrescar();
		}

		/**
		 * Metodo que se encarga de actualizar la parte gráfica
		 */
		public void refrescar() 
		{
			fireIntervalAdded(this, 0, data.getInstructions().length);
		}
	}
	
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar la lista de instrucciones
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaListaIns implements Runnable
	{
		private ProgramMV.Data data;
		
		public ActualizaListaIns(ProgramMV.Data ins)
		{
			data = ins;
		}
		

		@Override
		public void run() 
		{
			modeloLista.onStart(data);
		}
		
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar el contador de programa
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaCp implements Runnable
	{
		private ControlUnit.Data cp;
		
		public ActualizaCp(ControlUnit.Data cp)
		{
			this.cp = cp;
			
		}
		
		@Override
		public void run() 
		{
			modeloLista.onCPchange(cp);
			
		}
		
	}
	

	@Override
	public void onCPchange(Data cpData) 
	{	
		SwingUtilities.invokeLater(new ActualizaCp(cpData));
	}

	@Override
	public void onHalt() 
	{
		
	}

	@Override
	public void onStart(tp.pr5.mv.model.ProgramMV.Data instructions) 
	{
		SwingUtilities.invokeLater(new ActualizaListaIns(instructions));
	}

	@Override
	public void onInstructionStart(Instruction instr) 
	{
		
	}

	@Override
	public void onInstructionEnd(Instruction instr, tp.pr5.mv.model.Memory.Data mem,
			tp.pr5.mv.model.OperandStack.Data ops, Data pc) 
	{
		
	}

	@Override
	public void onError(Exception error) 
	{
		
	}
	
	
	//METODOS PRIVADOS
	
	//Construye el panel con todo lo necesario
	private void build()
	{
		modeloLista = new ModeloListaPrograma();
		listaPrograma = new JList<>(modeloLista);
		scroll = new JScrollPane(listaPrograma);
		
		this.setBorder(BorderFactory.createTitledBorder("Programa"));
		scroll.setPreferredSize(new Dimension(170, 585));
		
		this.add(scroll);
	}


}
