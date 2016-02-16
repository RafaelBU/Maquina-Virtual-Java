package tp.pr5.mv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.mv.controller.BatchController;
import tp.pr5.mv.controller.Controller;
import tp.pr5.mv.controller.InteractiveController;
import tp.pr5.mv.controller.cmdprompt.Prompt;
import tp.pr5.mv.io.FileInMethod;
import tp.pr5.mv.io.FileOutMethod;
import tp.pr5.mv.io.InMethod;
import tp.pr5.mv.io.NullInMethod;
import tp.pr5.mv.io.NullOutMethod;
import tp.pr5.mv.io.OutMethod;
import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.ProgramMV;
import tp.pr5.mv.model.instructions.io.In;
import tp.pr5.mv.model.instructions.io.Out;
import tp.pr5.mv.view.console.Console;
import tp.pr5.mv.view.console.io.StdInMethod;
import tp.pr5.mv.view.console.io.StdOutMethod;
import tp.pr5.mv.view.Swing.LauncherVentana;
import tp.pr5.mv.view.Swing.io.SwingInMethod;
import tp.pr5.mv.view.Swing.io.SwingOutMethod;

/**
 * Clase encargada de gestionar todo lo relacionado con los argumentos que 
 * se pueden introducir por consola
 * 
 * @author Rafael Buzón Urbano
 *  
 */
public class Parametros {
	
	private static Options options = new Options();
	private static CPU cpu = new CPU();
	private static ProgramMV program = new ProgramMV();
	private static Controller contr = new Controller(cpu);
	private static ProgramLoader programLoader = new ProgramLoader(contr);
	
	
	/**
	 * Añade los parametros que se van a usar y se encarga de parsear y ejecutarlas
	 * 
	 * @param args argumentos que pasamos a traves del main para su ejecucion
	 */
	public static void configuraInstrucciones(String[]args)
	{
		añadeInstrucciones(args,options);
		parsing(args);
	}
	
	
	//METODOS PRIVADOS
	
	//Nos añade las instrucciones o paramentros que vamos a utilizar
	private static void añadeInstrucciones(String[]args, Options options)
	{	
		options.addOption(creaAsm());
		options.addOption(creaHelp());
		options.addOption(creaIn());
		options.addOption(creaMode());
		options.addOption(creaOut());
		
	}
	
	//Se encarga del parseo y ejecucion de las instrucciones previamente añadidas
	private static void parsing(String[]args)
	{
		CommandLineParser parser = new BasicParser();
		 try {
			 
			InMethod entrada = new StdInMethod();
			OutMethod salida = new StdOutMethod();
			In.setEntrada(entrada);
			Out.setSalida(salida);
			
			boolean batch = true;
			boolean interactivo = false;
			boolean window = false;
			
			Prompt p = new Prompt();
			CommandLine cmd = parser.parse(options,args);
			
			
			OpcionHelp(cmd);
			
			//Si no hemos metido modo estamos en batch por defecto
			if(!cmd.hasOption("m"))
			{
				//Necesitamos el ASM obligatoriamente
				if(!cmd.hasOption("a"))
				{
					throw new ParseException("No se ha especificado fichero ASM");
				}
				else{
					//Modo entrada
					OpcionI(entrada, cmd, batch, window);
					
					//Modo salida
					OpcionO(salida, cmd, batch, window);
					
					program = programLoader.leeAsm(cmd.getOptionValue("a"), batch);
					
					//program = new ProgramMV(programLoader.getContenedor(), programLoader.getContador());
					cpu.loadProgram(program);
					
					Controller con = new BatchController(cpu);
					
					Console console = new Console(con);
					
					console.setBatch(batch);
					
					con.addCPUObserver(console);
					
					con.start();
				}
			}
			else{
				batch = cmd.getOptionValue("mode").equals("batch");
				//window = cmd.getOptionValue("mode").equals("window");
			}
			
			//Seleccionamos el modo de funcionamiento
			OpcionM(cmd, batch, interactivo, window, p, entrada, salida);

			
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	//Crea la opcion a
	private static Option creaAsm()
	{
		Option asm = OptionBuilder.withLongOpt("asm")
				  .withArgName("asmfile")
				  .hasArg(true)
				  .withDescription("Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch")
				  .create("a");
		
		return asm;
	}
	
	//Crea la opcion help
	private static Option creaHelp()
	{
		Option help = OptionBuilder.withLongOpt("help")
				.hasArg(false)
				.withDescription("Muestra esta ayuda")
				.create("h");
		
		return help;
	}
	
	//Crea la opcion in
	private static Option creaIn()
	{
		Option in = OptionBuilder.withLongOpt("in")
				 .withArgName("infile")
				 .hasArg(true)
				 .withDescription("Entrada del programa de la maquina-p")
				 .create("i");
		
		return in;
	}
	
	//Crea la opcion modo
	private static Option creaMode()
	{
		Option mode = OptionBuilder.withLongOpt("mode")
				.withArgName("mode")
				.hasArg(true)
				.withDescription("Modo de funcionamiento (batch|interactive|window) batch por defecto")
				.create("m");
		
		return mode;
	}

	//Crea la opcion out
	private static Option creaOut()
	{
		Option out  = OptionBuilder.withLongOpt("out")
				.withArgName("outfile")
				.hasArg(true)
				.withDescription("Fichero donde se guarda la salida del programa de la maquina-p")
				.create("o");
		
		return out;
	}
	
	//Muestra el menu de ayuda
	private static void menuAyuda(Options options)
	{
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] "
				+ " [-o <outfile>]", options );
	}
	
	//Se encarga de la configuracion de la opcion in
	private static void OpcionI(InMethod entrada, CommandLine cmd, boolean batch, boolean window)
	{
		if(cmd.hasOption("i"))
		{
			try {
				if(window)
				{
					entrada = new SwingInMethod(new FileInMethod(new FileInputStream(cmd.getOptionValue("i"))));
				}
				else{
					entrada = new FileInMethod(new FileInputStream(cmd.getOptionValue("i")));
				}
				In.setEntrada(entrada);
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			} catch(IOException i){
				System.err.println(i.getMessage());
				System.exit(1);
			}
		}
		else if(!batch)
		{
			entrada = new NullInMethod();

			In.setEntrada(entrada);
		}
	}
	
	//Se encarga de la configuracion de la opcion out
	private static void OpcionO(OutMethod salida, CommandLine cmd, boolean batch, boolean window)
	{
		if(cmd.hasOption("o"))
		{
			try {
				if(window)
				{
					salida = new SwingOutMethod(new FileOutMethod
							(new FileOutputStream(cmd.getOptionValue("o"))), true);
				}
				else{
					salida = new FileOutMethod(new FileOutputStream(cmd.getOptionValue("o")));
				}
				Out.setSalida(salida);
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
		}
		else if(window)
		{
			salida = new SwingOutMethod(salida, false);
			
			Out.setSalida(salida);
		}
		else if(!batch)
		{
			salida = new NullOutMethod();
			
			Out.setSalida(salida);
		}
	}
	
	//Se encarga de la configuracion de la opcion mode
	private static void OpcionM(CommandLine cmd, boolean batch, boolean interactivo, boolean window, Prompt p,
			InMethod entrada, OutMethod salida) throws ParseException
	{
		if(cmd.hasOption("m"))
		{
			batch = cmd.getOptionValue("mode").equals("batch");
			interactivo = cmd.getOptionValue("mode").equals("interactivo");
			window = cmd.getOptionValue("mode").equals("window");
			
			if(!batch && !interactivo && !window)
			{
				throw new ParseException("El parametro de la instruccion -m no es correcto");
			}
			
			java.util.Scanner input = new java.util.Scanner(System.in);
			//Modo entrada
			OpcionI(entrada, cmd, batch, window);
			
			//Modo salida
			OpcionO(salida, cmd, batch, window);
			
			if(batch)
			{
				if(cmd.hasOption("a"))
				{
					OpcionA(cmd, batch);
					cpu.loadProgram(program);
					
					Controller con = new BatchController(cpu);
					Console console = new Console(con);
					
					con.addCPUObserver(console);
		
					console.setBatch(batch);
					
					con.start();
					
					System.exit(0);
				}
				else{
					input.close();
					throw new ParseException("Fichero ASM no encontrado");
				}
			}
			else if(interactivo)
			{	
				
				if(!cmd.hasOption("a"))
				{
					programLoader.readProgram(input, batch);
					program = new ProgramMV(programLoader.getContenedor(), programLoader.getContador());
					
					cpu.loadProgram(program);
					
					Controller con = new InteractiveController(cpu, input);
					
					Console console = new Console(con);
					
					con.addCPUObserver(console);
					
					con.start();
					
					input.close();
					System.exit(0);
							
				}
				else{
					OpcionA(cmd, batch);
					cpu.loadProgram(program);
					
					Controller con = new InteractiveController(cpu, input);
					
					Console console = new Console(con);
					
					con.addCPUObserver(console);
					
					con.start();
					
					input.close();
					System.exit(0);
				}
			}
			else{
				if(!cmd.hasOption("a"))
				{
					input.close();
					throw new ParseException("No se puede cargar la ventana, fichero ASM no especificado");
				}
				else{
					Controller con = new Controller(cpu);
					
					LauncherVentana.lanzaVentana(con);
					OpcionA(cmd, true);
					cpu.loadProgram(program);
					
					con.start();
				}
				
			}
		}
	}
	
	//Se encarga de la configuracion de la opcion help
	private static void OpcionHelp(CommandLine cmd)
	{
		if(cmd.hasOption("h"))
		{
			menuAyuda(options);
			System.exit(0);
		}
	}
	
	//Se encarga de la configuracion de la opcion a
	private static void OpcionA(CommandLine cmd, boolean batch)
	{
		if(cmd.hasOption("a"))
		{
			program = programLoader.leeAsm(cmd.getOptionValue("a"), batch);
		}
	}
}
