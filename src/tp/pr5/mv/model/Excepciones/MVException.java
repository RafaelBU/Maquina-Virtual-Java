package tp.pr5.mv.model.Excepciones;

public class MVException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public MVException(String mensaje)
	{
		super(mensaje);
	}
	
	public MVException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public MVException(Throwable causa)
	{
		super(causa);
	}

}
