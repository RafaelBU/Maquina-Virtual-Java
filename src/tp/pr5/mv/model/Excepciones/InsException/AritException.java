package tp.pr5.mv.model.Excepciones.InsException;

public class AritException extends InsException{
	
	private static final long serialVersionUID = 1L;

	public AritException(String mensaje)
	{
		super(mensaje);
	}
	
	public AritException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public AritException(Throwable causa)
	{
		super(causa);
	}


}
