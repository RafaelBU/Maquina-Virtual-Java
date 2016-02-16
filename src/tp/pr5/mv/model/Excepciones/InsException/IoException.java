package tp.pr5.mv.model.Excepciones.InsException;

public class IoException extends InsException{
	
	private static final long serialVersionUID = 1L;

	public IoException(String mensaje)
	{
		super(mensaje);
	}
	
	public IoException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public IoException(Throwable causa)
	{
		super(causa);
	}

}
