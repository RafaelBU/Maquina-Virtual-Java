package tp.pr5.mv.model.Excepciones.InsException;

import tp.pr5.mv.model.Excepciones.MVException;

public class InsException extends MVException{
	
	
	private static final long serialVersionUID = 1L;

	public InsException(String mensaje)
	{
		super(mensaje);
	}

	public InsException(String mensaje, Throwable causa)
	{
		super(mensaje, causa);
	}
	
	public InsException(Throwable causa)
	{
		super(causa);
	}

}
