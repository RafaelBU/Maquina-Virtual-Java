package tp.pr5.mv.controller.cmdprompt.commands;

import tp.pr5.mv.controller.cmdprompt.PromptCommand;


/**
 * Comando primario encargado de ejecutar la siguiente instrucción. 
 * El resto de comandos heredarán de esta clase.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Step extends PromptCommand{
	
	public Step()
	{
		
	}
	
	/**
	 * Realiza el step mostrando los mensajes correspondientes. 
	 * La ejecución no se realiza si la CPU se encuentra detenida
	 * 
	 * @return Si la ejecución fue correcta
	 */
	protected void doStep()
	{
		
		if(cpu.isHalted())
		{
			return;
		}
		else{
			cpu.step();
		}
	}
	
	/**
	 * Ejecuta el comando delegando en el método doStep() para que pueda ser reutilizado por las clases hijas
	 */
	public void executeCommand()
	{
		doStep();
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Step en caso de que la cadena recibida 
	 * como parámetro sea "STEP" o null en caso contrario
	 * 
	 * @param Cadena de texto supuestamente representando el comando
	 * @return el objeto STEP o null en caso de no coincidencia
	 */
	public PromptCommand parse(String cadena)
	{
		cadena = cadena.trim();
		String [] partes = cadena.split("\\s+");
		
		if(partes[0].equalsIgnoreCase("STEP"))
		{
			if(partes.length == 1)
			{
				return new Step();
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
