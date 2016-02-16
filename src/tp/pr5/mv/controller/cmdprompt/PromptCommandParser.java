package tp.pr5.mv.controller.cmdprompt;


import tp.pr5.mv.controller.cmdprompt.commands.PopCommand;
import tp.pr5.mv.controller.cmdprompt.commands.PushCommand;
import tp.pr5.mv.controller.cmdprompt.commands.Run;
import tp.pr5.mv.controller.cmdprompt.commands.Step;
import tp.pr5.mv.controller.cmdprompt.commands.Steps;
import tp.pr5.mv.controller.cmdprompt.commands.WriteCommand;

/**
 * Clase encargada de analizar la entrada del usuario y generar el comando correspondiente. 
 * Guarda internamente un array con todos los comandos disponibles
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class PromptCommandParser {

	private static PromptCommand[] comandos = {new Step(), new Steps(), new Run(), new PushCommand(), 
											   new PopCommand(), new WriteCommand()};
	public PromptCommandParser()
	{
		
	}
	
	/**
	 * Analiza la orden introducida por el usuario y genera el comando correspondiente. 
	 * Devuelve null en caso de error.
	 * 
	 * @param line Es la orden introducida por el usuario
	 * @return El comando correspondiente o null en caso de error al analizar la orden en el parámetro line
	 */
	public static PromptCommand parseCommand(String line)
	{
		for(PromptCommand p : comandos)
		{
			PromptCommand comando = p.parse(line);
			if(comando != null)
			{
				return comando;
			}
		}
		return null;
	}
}
