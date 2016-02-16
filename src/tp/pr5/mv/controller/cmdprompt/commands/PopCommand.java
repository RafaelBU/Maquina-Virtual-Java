package tp.pr5.mv.controller.cmdprompt.commands;


import tp.pr5.mv.controller.cmdprompt.PromptCommand;
import tp.pr5.mv.model.instructions.stack.Pop;

/**
 * Comando encargado de desapilar de la pila
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PopCommand extends Step {
	
	public PopCommand()
	{
		
	}
	
	public void executeCommand()
	{
		cpu.stepAux(new Pop());
	}
	
	public PromptCommand parse(String line)
	{
		line = line.toUpperCase().trim();
		String[]partes = line.split("\\s+");
		
		if(partes[0].equals("POP"))
		{
			if(partes.length == 1)
			{
				return new PopCommand();
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}


}
