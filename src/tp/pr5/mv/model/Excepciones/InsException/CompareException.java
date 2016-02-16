package tp.pr5.mv.model.Excepciones.InsException;

public class CompareException extends InsException {
	
	private static final long serialVersionUID = 1L;

	public CompareException(String mensaje)
	{
		super(mensaje);
	}
	
	public CompareException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public CompareException(Throwable causa)
	{
		super(causa);
	}

}
