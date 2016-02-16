package tp.pr5.mv.view.Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.mv.controller.Controller;

/**
 * Clase que representa el panel de acciones de la ventana de la maquina virtual
 * Sus 4 botones representan las acciones de Step, Run, Salir y Pause
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PanelAcciones extends JPanel implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Botones que servirán para realizar las distintas acciones
	 */
	private JButton botonSalida;
	private JButton botonStep;
	private JButton botonRun;
	private JButton botonPause;
	
	/**
	 * Atributos que nos dan varias funcionalidades necesarias
	 */
	private Controller controller;
	private EncargadaRun hebraRun;
	private boolean ejecutando;
	
	/**
	 * Constructor que se encarga de inicializar los botones, ponerlos activos, añadirlos al panel
	 * y ponerle unos iconos personalizados a esos botones
	 * 
	 * @param controller controlador que se encargará de realizar las distintas acciones de los botones
	 */
	public PanelAcciones(Controller controller)
	{
		build();
		
		this.controller = controller;
		hebraRun = new EncargadaRun();
		ejecutando = true;
	
	}
	
	/**
	 * Metodo que cambia el indicador de estado de ejecución de la hebra
	 * 
	 * @param estado estado que se va a cambiar
	 */
	public void setEstado(boolean estado)
	{
		ejecutando = false;
		//
		hebraRun.interrupt();
	}
	
	/**
	 * Metodo que habilita los botones de run y step
	 */
	public void habilita()
	{
		botonStep.setEnabled(true);
		botonRun.setEnabled(true);
				
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == botonStep)
		{
			controller.performStep();
			compruebaYDesabilitaBotones();
		}
		else if(e.getSource() == botonRun)
		{
			desabilita();
			if(ejecutando)
			{
				hebraRun.start();
			}
			else if(!ejecutando || !hebraRun.isAlive())
			{
				EncargadaRun hebraNueva = new EncargadaRun();
				hebraRun = hebraNueva;
					
				hebraRun.start();
			}
			
		}
		else if(e.getSource() == botonPause)
		{
			habilita();
			hebraRun.interrupt();
			ejecutando = false;
			
		}
		else{
			int i = JOptionPane.showConfirmDialog(this,"¿Realmente desea salir de la maquina virtual?","Confirmar Salida",JOptionPane.YES_NO_OPTION);
			if(i == 0)
			{
				System.exit(0);
			}
		}
		
	}
	
	/**
	 * Clase que nos proporciona la funcionalidad de tener una hebra que se encargue de la ejecución
	 * de la lógica de nuestro programa
	 * 
	 * @author Rafael Buzón Urbano
	 *
	 */
	public class EncargadaRun extends Thread
	{	
		public void run()
		{
			try {
				controller.performRun();
				
			} catch (InterruptedException e) {
	
			}
			compruebaYDesabilitaBotones();
		}
		
	}
	
	//METODOS PRIVADOS
	
	//Nos crea un boton con un texto y una imagen determinada
	private JButton createIconButton(String texto, String filename) 
	{
		URL url = Ventana.class.getResource(filename);
		if (url != null) {
			ImageIcon icon = new ImageIcon(url);
			JButton boton = new JButton(texto, icon);
			return boton;
		}
		
		return null;
	}
	
	//Se encarga de construir el panel con todos sus elementos
	private void build()
	{
		botonSalida = createIconButton("", "iconos/exit.png");
		botonStep = createIconButton("", "iconos/step.png");
		botonRun = createIconButton("", "iconos/run.png");
		botonPause = createIconButton("", "iconos/pause.png");
			
		this.setBorder(BorderFactory.createTitledBorder("Acciones"));
			
		botonStep.addActionListener(this);
		botonRun.addActionListener(this);
		botonSalida.addActionListener(this);
		botonPause.addActionListener(this);
			
		this.add(botonSalida);
		this.add(botonStep);
		this.add(botonRun);
		this.add(botonPause);
	}
		
	//Desabilita todos los botones cuando la maquina esta parada(ha acabado la ejecución o hemos encontrado
	//una instruccion halt)
	private void compruebaYDesabilitaBotones()
	{
		if(controller.performIsHalted())
		{
			botonStep.setEnabled(false);
			botonRun.setEnabled(false);
			botonPause.setEnabled(false);
		}
		
	}
	
	//Desabilita los botones run y step
	private void desabilita()
	{
		botonStep.setEnabled(false);
		botonRun.setEnabled(false);
	}
	

}
