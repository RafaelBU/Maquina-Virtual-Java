package tp.pr5.mv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.model.ProgramMV;
import tp.pr5.mv.view.console.Console;

/**
 * Clase auxiliar encargada de la lectura de las instrucciones
 * 
 * @author Rafael Buzon Urbano
 *
 */
public class ProgramLoader{
	
	private Console console;
	private Instruction [] contenedor;
	private int contador;
	private Controller controller;
	
	public ProgramLoader(Controller con) 
	{
		console = new Console(con);
		contenedor = new Instruction[100];
		contador = 0;
		this.controller = con;
	}

	public void readProgram(java.util.Scanner input, boolean batch)
	{
		console.readProgram(input, batch);
		contenedor = console.getContenedor();
		contador = console.getContador();
	}
	
	public Instruction[] getContenedor()
	{
		return contenedor;
	}
	
	public int getContador()
	{
		return contador;
	}
	
	/**
	 * Metodo que se encarga de leer las instrucciones de un fichero asm
	 * 
	 * @param file fichero de donde se va a leer
	 * @param batch modo de ejecucion
	 * @return programa con las instrucciones cargadas
	 */
	public ProgramMV leeAsm(String file, boolean batch)
	{
		Scanner scanner;
		try {
			scanner = new Scanner(new File(file));
			StringBuffer contenido = new StringBuffer();
			
			while(scanner.hasNext())
			{
				String linea =  scanner.nextLine();
				if(!linea.startsWith(";")){
					if(linea.contains(";"))
					{
						String[]partes = linea.split(";");
						contenido.append(partes[0]);
						contenido.append("\n");
					}
					else{
						contenido.append(linea);
						contenido.append("\n");
					}
				}
			}
			scanner.close();
		
			readProgram(new Scanner(contenido.toString()), batch);
			return new ProgramMV(Arrays.copyOf(contenedor, contador), contador);
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(2);
			return null;
		}
	}
	
	
}
