package tp.pr5.mv.controller.cmdprompt.commands;

import tp.pr5.mv.controller.cmdprompt.PromptCommand;

/**
 * Comando encargado de ejecutar tantas instrucciones como sea posible hasta que la cpu se detenga
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Run extends Step {

	public Run()
	{
		
	}
	
	/**
	 * Ejecuta el método heredado doStep() hasta que la cpu se detenga. 
	 * En caso de error al ejecutar una instrucción no se ejecutan más instrucciones.
	 * 
	 * @return Si la ejecución fue correcta
	 */
	public void executeCommand()
	{
		while(!(cpu.isHalted()) && !cpu.error() )
		{
			doStep();
		}
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Run en caso de que la cadena recibida 
	 * como parámetro sea "RUN" o null en caso contrario
	 * 
	 * @param Cadena de texto supuestamente representando el comando
	 * @return El objeto RUN o null en caso de no coincidencia
	 */
	public PromptCommand parse(String cadena)
	{
		cadena = cadena.trim();
		String [] partes = cadena.split("\\s+");
		
		if(partes[0].equalsIgnoreCase("RUN"))
		{
			return new Run();
		}
		else{
			return null;
		}
	}
}
