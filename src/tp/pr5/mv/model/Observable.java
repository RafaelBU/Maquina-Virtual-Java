package tp.pr5.mv.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Todas las clases que puedan ser observadas deben heredar de esta clase.
 * Añade una lista de observadores protegida y métodos públicos para añadir
 * y eliminar observadores.
 */

public class Observable<I> {
	
	protected List<I> observers;
	
	public Observable()
	{
		observers = new ArrayList<I>();
	}
	
	/**
	 * Añade un nuevo observador a la lista; si el observador ya
	 * existía, la operación no tiene efecto.
	 * 
	 * @param observer Nuevo observador.
	 */
	public void addObserver(I observer) 
	{
		if(!observers.contains(observer))
		{
			observers.add(observer);
		}
	}
	
	/**
	 * Elimina de la lista el observador indicado; si el observador
	 * no estaba regitrado, la operación no tiene efecto.
	 * 
	 * @param observer Observador a eliminar.
	 */
	public void removeObserver(I observer) 
	{
		if(!observers.contains(observer))
		{
			observers.remove(observer);
		}
	}

}
