package tp.pr5.mv.model.Excepciones;

public class StackException extends MVException{
	
	private static final long serialVersionUID = 1L;

	public StackException(String mensaje)
	{
		super(mensaje);
	}
	
	public StackException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public StackException(Throwable causa)
	{
		super(causa);
	}
}
