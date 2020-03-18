package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dao.ClientDAO;
import model.Client;
import validators.EmailValidator;
import validators.Validator;
import validators.VarstaClientValidator;

public class ClientBLL {

	private ArrayList<Validator<Client>> validatori;

	public ClientBLL() {
		validatori = new ArrayList<Validator<Client>>();
		validatori.add(new EmailValidator());
		validatori.add(new VarstaClientValidator());
	}

	public Client findClientById(int id) {
		Client c = new ClientDAO().findById(id);

		if (c == null) {
			throw new NoSuchElementException("Nu exista client cu acest id : " + id);
		}

		return c;
	}

	public Client insert(Client c) {
		try {
			validatori.get(0).validate(c);
			validatori.get(1).validate(c);
			return new ClientDAO().insert(c);
		} catch (Exception e) {
			return null;
		}
	}

	public int delete(int id) {
		int c = new ClientDAO().delete(id);

		if (c == 0) {
			throw new NoSuchElementException("Nu exista clientul acesta!");
		}
		return c;
	}

	public List<Client> findall() {
		List<Client> clienti = new ArrayList<Client>();
		try {
		clienti = new ClientDAO().findAll();
		}
		catch(Exception e) {
			throw new NoSuchElementException("Nu sunt clienti deloc :( ");
		}
		return clienti;
	}
	
	public Client update(Client c) {
		try {
		Client c1 = new ClientDAO().update(c);
		return c1;

		}
		catch(Exception e) {
			throw new NoSuchElementException("Nu exista acest client petru a-l updata");
		}
	}
}
