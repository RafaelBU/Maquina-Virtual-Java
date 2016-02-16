package tp.pr5.mv.view.Swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.model.CPU.Observer;
import tp.pr5.mv.model.ProgramMV.Data;
import tp.pr5.mv.view.Swing.io.SwingInMethod;
import tp.pr5.mv.view.Swing.io.SwingOutMethod;

/**
 * Clase que representan la ventana principal de nuestra interfaz gráfica
 * Esta formada por tres paneles generales(panel de acciones, panel de programa y panel central)
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Ventana extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Los distintos paneles que forman parte de la ventana
	 */
	private PanelAcciones panelAcciones;
	private PanelPrograma panelPrograma;
	private PanelCentral panelCentral;
	private PanelInfo panelInfo;
	
	/**
	 * Constructor que le da título a la ventana, un tamaño determinado y se encarga
	 * además de inicializar los paneles y añadirlos
	 * Inicializa ademas por separado los panel de entrada y de salida
	 * 
	 * @param controller controlador que realizará distintas acciones en los paneles
	 */
	public Ventana (Controller controller)
	{
		super("Maquina Virtual TP");
		
		build(controller);
		
		controller.addCPUObserver(this);
			
		initIO();
	}
	
	
	/**
	 * Método que delega en el panel central para poder realizar su acción 
	 * 
	 * @param buffer string que necesitamos para poder realizar la acción
	 */
	public void muestraEntrada(String buffer)
	{
		panelCentral.muestraEntrada(buffer);
	}
	
	/**
	 * Método que delega en el panel central para poder realizar su acción 
	 * 
	 * @param indice valor que necesitamos para realizar la acción
	 */
	public void consumeEntrada(int indice)
	{
		panelCentral.consumeEntrada(indice);
	}
	
	/**
	 * Método que delega en el panel central para poder realizar su acción 
	 * 
	 * @param c char que necesitamos para poder realizar la acción
	 */
	public void produceSalida(char c)
	{
		panelCentral.produceSalida(c);
	}

	@Override
	public void onStart(Data instructions) 
	{
		
	}

	@Override
	public void onInstructionStart(Instruction instr) 
	{
		panelInfo.onInstructionStart(instr);
	}

	@Override
	public void onInstructionEnd(Instruction instr, tp.pr5.mv.model.Memory.Data mem,
			tp.pr5.mv.model.OperandStack.Data ops, tp.pr5.mv.model.ControlUnit.Data pc) 
	{
		
	}

	@Override
	public void onError(Exception error) 
	{
		JOptionPane.showMessageDialog(this, error.getMessage(), "Error de ejecución", JOptionPane.ERROR_MESSAGE);
		panelAcciones.habilita();
		panelAcciones.setEstado(false);
	}
	
	//METODOS PRIVADOS
	
	//Construye la ventana con los componentes necesarios
	private void build(Controller controller)
	{
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAcciones = new PanelAcciones(controller);
		panelPrograma = new PanelPrograma(controller);
		panelCentral = new PanelCentral(controller);
		panelInfo = new PanelInfo(controller);
		
		this.add(panelAcciones, BorderLayout.NORTH);
		this.add(panelPrograma, BorderLayout.WEST);
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelInfo, BorderLayout.SOUTH);
	}
	
	//Inicializa el panel de entrada y el de salida
	private void initIO()
	{
		SwingInMethod.setVentana(this);
		SwingOutMethod.setVentana(this);
	}

}
