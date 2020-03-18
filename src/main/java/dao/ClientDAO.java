package dao;

import java.util.List;

import model.Client;

public class ClientDAO extends AbstractDAO<Client> {

	@Override
	public Client insert(Client t) {
		// TODO Auto-generated method stub
		return super.insert(t);
	}

	@Override
	public Client findById(int id) {
		// TODO Auto-generated method stub
		System.out.println("lala");
		return super.findById(id);
	}

	@Override
	public Client update(Client t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return super.delete(id);
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
