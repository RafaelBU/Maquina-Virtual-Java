package tp.pr5.mv.model.Excepciones;

public class MemoryException extends MVException{
	
	private static final long serialVersionUID = 1L;

	public MemoryException(String mensaje)
	{
		super(mensaje);
	}
	
	public MemoryException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public MemoryException(Throwable causa)
	{
		super(causa);
	}

}
