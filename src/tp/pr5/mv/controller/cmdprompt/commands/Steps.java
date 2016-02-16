package tp.pr5.mv.controller.cmdprompt.commands;

import tp.pr5.mv.controller.cmdprompt.PromptCommand;

/**
 * Comando encargado de ejecutar n instrucciones
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Steps extends Step {

	private int numeroSteps;
	
	public Steps()
	{
		
	}
	
	public Steps(int nsteps)
	{
		this.numeroSteps = nsteps;
	}
	
	/**
	 * Ejecuta el método heredado doStep() el número de veces indicado al construir el objeto. 
	 * En caso de error al ejecutar una instrucción no se ejecutan más instrucciones
	 * 
	 * @return true si todas las ejecuciones se realizaron correctamente. False en caso contrario.
	 */
	public void executeCommand()
	{
		for(int i = 0; i < this.numeroSteps; i++)
		{
			doStep();
		}
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Steps en caso de que la cadena recibida 
	 * como parámetro sea "Step N" o null en caso contrario
	 * 
	 * @param Cadena de texto supuestamente representando el comando
	 * @return el objeto Steps o null en caso de no coincidencia
	 */
	public PromptCommand parse(String cadena)
	{
		cadena = cadena.trim();
		
		String[] partes = cadena.split("\\s+");
		
		if(partes[0].equalsIgnoreCase("STEP"))
		{
			if(partes.length == 2)
			{
				numeroSteps = Integer.parseInt(partes[1]);
				return new Steps(this.numeroSteps);
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
