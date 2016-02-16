package tp.pr5.mv.model.Excepciones.InsException;

public class LogicException extends InsException {
	
	private static final long serialVersionUID = 1L;

	public LogicException(String mensaje)
	{
		super(mensaje);
	}
	
	public LogicException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public LogicException(Throwable causa)
	{
		super(causa);
	}

}
