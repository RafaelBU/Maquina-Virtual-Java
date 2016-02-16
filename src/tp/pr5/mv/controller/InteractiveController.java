package tp.pr5.mv.controller;

import java.util.Scanner;

import tp.pr5.mv.controller.cmdprompt.Prompt;
import tp.pr5.mv.model.CPU;

/**
 * Clase que representa el controlador en el modo interactivo 
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class InteractiveController extends Controller {

	private Scanner input;
	private Prompt pr;
	
	/**
	 * Contructor
	 * 
	 * @param model cpu que vamos a usar
	 * @param sc scanner de entrada del programa
	 */
	public InteractiveController(CPU model, Scanner sc) 
	{
		super(model);
		this.cpu = model;
		this.input = sc;
		pr = new Prompt();
	}
	
	@Override
	public void start() 
	{
		super.start();
		
		pr.runPrompt(input, cpu);
		
	}
	
	
}
