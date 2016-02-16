package tp.pr5.mv.model.Excepciones;

public class ControlUnitException extends MVException{
	
	private static final long serialVersionUID = 1L;

	public ControlUnitException(String mensaje)
	{
		super(mensaje);
	}
	
	public ControlUnitException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public ControlUnitException(Throwable causa)
	{
		super(causa);
	}


}
