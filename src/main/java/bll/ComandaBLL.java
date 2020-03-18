package bll;

import dao.ComandaDAO;
import model.Comanda;
import validators.CantitateComandaValidator;
import validators.Validator;

public class ComandaBLL {

	public Validator<Comanda> validator;
	
	public ComandaBLL() {
		validator =new CantitateComandaValidator();
	}
	
	public Comanda insert(Comanda c) {
		try {
			Comanda c1 = new ComandaDAO().insert(c);
			return c1;
		}
		catch(Exception e) {
			return null;
		}
	}
	
}
