package validators;

import model.Comanda;

public class CantitateComandaValidator implements Validator<Comanda> {

	public void validate(Comanda t) {
       if(t.getCantitate() <= 0) {
    	   throw new IllegalArgumentException("Cantitatea trebuie sa fie strict mai mare decat 0!!");
       }
	}

}
