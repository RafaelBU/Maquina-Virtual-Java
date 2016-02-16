package tp.pr5.mv.controller.cmdprompt;

import tp.pr5.mv.model.CPU;

/**
 * Clase encargada de ejecutar el prompt que controla la ejecución del programa.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Prompt {

	private static final java.lang.String MSG_PROMPT = "> ";
	private static final java.lang.String MSG_PARSE_ERROR = "Error: Comando no reconocido";
	private static final java.lang.String END_TOKEN = "QUIT";
	private PromptCommand comand;
	
	public Prompt()
	{
		
	}
	
	/**
	 * Método encargado de ejecutar el prompt para leer los comandos del usuario. 
	 * Muestra al usuario el prompt (>) y los mensajes de información o error. 
	 * Termina cuando el usuario introduce el valor definido por la constante END_TOKEN.
	 * 
	 * @param input de entrada desde donde leer los comandos del usuario
	 * @param cpu sobre la que se ejecutan los comandos.
	 */
	public void runPrompt(java.util.Scanner input, CPU cpu)
	{
		String cadena;
		PromptCommand.configureCommandInterpreter(cpu);//Configuramos la cpu
		System.out.print(MSG_PROMPT);
		cadena = input.nextLine();
	
		while(!(cadena.equalsIgnoreCase(END_TOKEN)))
		{
			comand = PromptCommandParser.parseCommand(cadena);
			if(comand == null)
			{
				System.out.println(MSG_PARSE_ERROR);
			}
			else{
				comand.executeCommand();
				if(cpu.isHalted())
				{
					//Si se ha parado la cpu, salimos
					System.out.print(MSG_PROMPT);
					return;
				}
			}

			System.out.print(MSG_PROMPT);
			cadena = input.nextLine();
		}
	}
}
