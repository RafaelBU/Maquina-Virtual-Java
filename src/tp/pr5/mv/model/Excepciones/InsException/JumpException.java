package tp.pr5.mv.model.Excepciones.InsException;

public class JumpException extends InsException {
	
	private static final long serialVersionUID = 1L;

	public JumpException(String mensaje)
	{
		super(mensaje);
	}
	
	public JumpException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public JumpException(Throwable causa)
	{
		super(causa);
	}

}
