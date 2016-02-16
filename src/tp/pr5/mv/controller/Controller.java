package tp.pr5.mv.controller;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.ControlUnit;
import tp.pr5.mv.model.Memory;
import tp.pr5.mv.model.OperandStack;
import tp.pr5.mv.model.instructions.memory.Write;
import tp.pr5.mv.model.instructions.stack.Pop;
import tp.pr5.mv.model.instructions.stack.Push;

/**
 * Clase que representar el controlador central de la aplicación
 * Sirve de puerta de enlace entre la vista y el modelo
 * 
 * @author Rafael Buzón Urbano
 *
 */
public class Controller {
	
	protected CPU cpu;
	
	/**
	 * Constructor, recibe como parametro la CPU
	 * 
	 * @param model cpu que queremos conectar con la vista
	 */
	public Controller(CPU model) 
	{
		this.cpu = model;
	}

	/**
	 * Metodo del controlador que llama a la instruccion push de la cpu
	 * 
	 * @param v valor que queremos almacenar en la pila
	 */
	public void performPush(int v) 
	{
		cpu.stepAux(new Push(v));
	}

	/**
	 * Metodo del controlador que llama a la instruccion pop de la cpu
	 */
	public void performPop() 
	{
		cpu.stepAux(new Pop());
	}

	/**
	 * Metodo del controlador que llama a la instruccion write de la cpu
	 * 
	 * @param pos posicion de memoria
	 * @param val valor que queremos escribir
	 */
	public void performWrite(int pos, int val) 
	{
		cpu.stepAux(new Write(pos, val));
	}
	

	/**
	 * Metodo que añade un observador
	 * 
	 * @param obs
	 */
	public void addStackObserver(OperandStack.Observer obs) 
	{
		cpu.addObserver(obs);
	}

	/**
	 * Metodo que añade un observador
	 * 
	 * @param obs
	 */
	public void addMemoryObserver(Memory.Observer obs) 
	{
		cpu.addObserver(obs);
	}

	/**
	 * Metodo que añade un observador
	 * 
	 * @param obs
	 */
	public void addCPUObserver(CPU.Observer obs) 
	{
		cpu.addObserver(obs);
	}
	
	/**
	 * Metodo que añade un observador
	 * 
	 * @param obs
	 */
	public void addControlUnitObserver(ControlUnit.Observer obs) 
	{
		cpu.addObserver(obs);
	}

	/**
	 * Metodo del controlador que llama al step de la cpu
	 * 
	 * @return true si todo ha ido bien, false si se ha producido algun error
	 */
	public void performStep()
	{
		cpu.step();
	}
	
	/**
	 * Metodo del controlador que llama el performstep hasta que la cpu este parada
	 * Duerme la hebra durante 1 segundo para poder ver la evolucion de lo que sucede durante la ejecución
	 * 
	 * @throws InterruptedException
	 */
	public void performRun() throws InterruptedException
	{
		while (!cpu.isHalted())
		{
			performStep();
			Thread.sleep(1000);
		}
	}
	
	/**
	 * Metodo del controlador que llama al isHalted de la cpu
	 * 
	 * @return true si la cpu esta parada, false en caso contrario
	 */
	public boolean performIsHalted()
	{
		return cpu.isHalted();
	}
	
	/**
	 * Metodo que llama al start de la cpu
	 */
	public void start()
	{
		cpu.start();
	}

}
