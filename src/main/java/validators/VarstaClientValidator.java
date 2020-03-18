package validators;

import model.Client;

public class VarstaClientValidator implements Validator<Client> {

	private static final int VARSTA_MINIMA = 18;
	
	public void validate(Client c) {
		if(c.getVarsta() < VARSTA_MINIMA) {
			throw new IllegalArgumentException("Clientul nu e major!!");
		}
	}
}
