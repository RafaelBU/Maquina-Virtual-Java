package tp.pr5.mv;

/**
 * Clase principal que únicamente incluye el método main.
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Main {

	/**
	 * Método principal de la aplicación. 
	 * Ejecuta el bucle principal encargado de leer las instrucciones y ejecutarlas.
	 * Los pasos son los siguientes:
	 * 
     * * Leer el programa a través de ProgramMV
     * * Crear la CPU y asignarle el programa
     * * Ejecutar el prompt
     * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Parametros.configuraInstrucciones(args);
	}

}
