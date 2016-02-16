package tp.pr5.mv.controller.cmdprompt.commands;


import tp.pr5.mv.controller.cmdprompt.PromptCommand;
import tp.pr5.mv.model.instructions.stack.Push;

/**
 * Comando encargado de apilar en la pila 
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PushCommand extends Step {
	
	private int parametro;
	
	public PushCommand()
	{
		this(0);
	}
	
	public PushCommand(int parametro)
	{	
		this.parametro = parametro;
	}
	
	public void executeCommand()
	{
		cpu.stepAux(new Push(parametro));
	}

	public PromptCommand parse(String line)
	{
		line = line.toUpperCase().trim();
		String[]partes = line.split("\\s+"); 
		
		if((partes.length == 2) && (partes[0].equals("PUSH"))) 
		{
			parametro = Integer.parseInt(partes[1]);
			return new PushCommand(parametro);
		}
		return null;
	}
}
