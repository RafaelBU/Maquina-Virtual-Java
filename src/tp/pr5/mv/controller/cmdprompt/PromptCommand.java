package tp.pr5.mv.controller.cmdprompt;

import tp.pr5.mv.model.CPU;


/**
 * Clase abstracta que define los métodos a implementar por los comandos
 * 
 * @author Rafael Buzón Urbano
 *
 */
public abstract class PromptCommand {

	/**
	 * Atributo estatico donde se guarda la CPU sobre la que se ejecutan los comandos
	 */
	protected static CPU cpu;
	
	public PromptCommand()
	{
		
	}
	
	/**
	 * Establece la cpu sobre la que se ejecutarán todos los comandos
	 * 
	 * @param cpuA
	 */
	protected static void configureCommandInterpreter(CPU cpuA)
	{
		cpu = cpuA;
	}
	
	
	//METODOS ABSTRACTOS
	
	/**
	 * Ejecuta el comando
	 * 
	 * @return Si la ejecución fue correcta
	 */
	public abstract void executeCommand();
	
	/**
	 * Devuelve el comando correspondiente a la cadena recibida como parámetro o null en caso contrario
	 * 
	 * @param cadena De texto supuestamente representando un comando
	 * @return El comando correspondiente o null en caso de no coincidencia
	 */
	public abstract PromptCommand parse(String cadena);
}
