package tp.pr5.mv.controller.cmdprompt.commands;


import tp.pr5.mv.controller.cmdprompt.PromptCommand;
import tp.pr5.mv.model.instructions.memory.Write;

/**
 * Comando encargado de escribir en memoria
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class WriteCommand extends Step{
	
	private int pos, value;
	
	public WriteCommand()
	{
		this(0, 0);
	}
	
	public WriteCommand(int pos, int value)
	{
		this.pos = pos;
		this.value = value;
	}
	
	public void executeCommand()
	{
		cpu.stepAux(new Write(pos, value));
	}
	
	public PromptCommand parse(String line)
	{
		line = line.toUpperCase().trim();
		String[]partes = line.split("\\s+"); 
		
		if((partes.length == 3) && (partes[0].equals("WRITE")))
		{
			pos = Integer.parseInt(partes[1]);
			value = Integer.parseInt(partes[2]);
			return new WriteCommand(pos, value);
		}
		return null;
	}


}
