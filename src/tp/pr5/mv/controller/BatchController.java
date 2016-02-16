package tp.pr5.mv.controller;

import tp.pr5.mv.model.CPU;

/**
 * Clase que representar al controlador del modo batch
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class BatchController extends Controller{

	private CPU cpu;
	
	/**
	 * Constructor
	 * 
	 * @param model cpu que queremos utilizar
	 */
	public BatchController(CPU model) 
	{
		super(model);
		this.cpu = model;
	}
	
	@Override
	public void start() 
	{
		super.start();
		while(!cpu.isHalted())
		{
			cpu.step();
		}
	}

}
