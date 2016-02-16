package tp.pr5.mv.view.Swing;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.CPU.Observer;
import tp.pr5.mv.model.ProgramMV.Data;

/**
 * Clase que representa el panel de información de nuestra interfaz gráfica
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelInfo extends JPanel implements Observer, OperandStack.Observer, Memory.Observer, 
														ControlUnit.Observer {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Distintos componentes graficos para la construcción del panel
	 */
	private JLabel estadoMaquina;
	private JLabel instruccionesEj;
	private JLabel numInstrucciones;
	private JLabel memoria;
	private JLabel pila;
	private JCheckBox boxMemoria;
	private JCheckBox boxPila;
	
	/**
	 * Constructor
	 * 
	 * @param controller controlador que servira para registrar el panel como observador
	 */
	public PanelInfo(Controller controller)
	{
		build();
		
		controller.addControlUnitObserver(this);
		controller.addMemoryObserver(this);
		controller.addStackObserver(this);
		controller.addCPUObserver(this);
	}
	
	

	@Override
	public void onStart(Data instructions) 
	{
		
	}

	@Override
	public void onInstructionStart(Instruction instr)
	{
		SwingUtilities.invokeLater(new ActualizaBox());
		
	}

	@Override
	public void onInstructionEnd(Instruction instr, tp.pr5.mv.model.Memory.Data mem,
			tp.pr5.mv.model.OperandStack.Data ops, tp.pr5.mv.model.ControlUnit.Data pc) 
	{
		
	}

	@Override
	public void onError(Exception error) 
	{
		
	}

	@Override
	public void onCPchange(tp.pr5.mv.model.ControlUnit.Data cpData) 
	{
		SwingUtilities.invokeLater(new ActualizaNumIns(cpData));
	}

	@Override
	public void onHalt() 
	{
		SwingUtilities.invokeLater(new ActualizaEstadoMaquina());
		
	}

	@Override
	public void onMemoryChange(tp.pr5.mv.model.Memory.Data data) 
	{
		SwingUtilities.invokeLater(new ActualizaMemoriaPanel());
	}

	@Override
	public void onStackChange(tp.pr5.mv.model.OperandStack.Data data) 
	{
		SwingUtilities.invokeLater(new ActualizaPilaPanel());
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar el box de la pila
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaPilaPanel implements Runnable{
		
		public ActualizaPilaPanel()
		{
			
		}

		@Override
		public void run() 
		{
			boxPila.setSelected(true);
		}
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar el box de la memoria
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaMemoriaPanel implements Runnable{
		
		public ActualizaMemoriaPanel()
		{
			
		}

		@Override
		public void run() 
		{
			boxMemoria.setSelected(true);
		}
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar el estado de la maquina
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaEstadoMaquina implements Runnable{
		
		public ActualizaEstadoMaquina()
		{
			
		}

		@Override
		public void run() 
		{
			estadoMaquina.setText("Maquina parada");
			estadoMaquina.setForeground(Color.red);
		}
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar el numero de instrucciones
	 * ejecutadas
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaNumIns implements Runnable{

		private ControlUnit.Data cpData;
		
		public ActualizaNumIns(ControlUnit.Data cpData)
		{
			this.cpData = cpData;
		}
		
		@Override
		public void run() 
		{
			numInstrucciones.setText(" " + Integer.toString(cpData.getNumInstrucciones()));
		}
		
	}
	
	/**
	 * Clase que implementando a Runnable nos da la funcionalidad de poder actualizar el box de la memoria
	 * y de la pila
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	class ActualizaBox implements Runnable{

		public ActualizaBox()
		{
			
		}
		
		@Override
		public void run() 
		{
			boxMemoria.setSelected(false);
			boxPila.setSelected(false);
			
		}
		
	}
	
	//METODOS PRIVADOS
	
	//Construye el panel con los elementos necesarios
	private void build()
	{
		
		estadoMaquina = new JLabel("              ");
		instruccionesEj = new JLabel("Num. Intrucciones ejecutadas:");
		numInstrucciones = new JLabel("  0");
		memoria = new JLabel("Memoria de la maquina");
		pila = new JLabel("Pila de la maquina");
		boxMemoria = new JCheckBox();
		boxPila = new JCheckBox();
						
		this.add(estadoMaquina);
		
		this.add(instruccionesEj);
		this.add(numInstrucciones);
		
		this.add(memoria);
		this.add(boxMemoria);
		
		this.add(pila);
		this.add(boxPila);
	}
	

}
