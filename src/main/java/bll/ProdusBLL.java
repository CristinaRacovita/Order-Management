package bll;

import java.util.List;

import model.Produs;
import java.util.NoSuchElementException;

import dao.ProdusDAO;
import model.Produs;

public class ProdusBLL {

	public Produs findById(int id) {
		Produs p = new ProdusDAO().findById(id);

		if (p == null) {
			throw new NoSuchElementException("Nu exista o comanda cu un astfel de id!");
		}
		return p;
	}

	public Produs insert(Produs p) {
		try {
			Produs p1 = new ProdusDAO().insert(p);
			return p1;
		} catch (Exception e) {
			return null;
		}
	}

	public int delete(int p) {
		try {
			int p1 = new ProdusDAO().delete(p);
			return p1;
		} catch (Exception e) {
			throw new NoSuchElementException("Nu exista un astfel de produs!");
		}
	}

	public List<Produs> findall() {
		try {
			List<Produs> p = new ProdusDAO().findAll();
			return p;
		} catch (Exception e) {
			throw new NoSuchElementException("Nu exista elemente in tabel");
		}
	}
	
	public Produs update(Produs p) {
		try {
			Produs p1 = new ProdusDAO().update(p);
			return p1;
		}
		catch(Exception e) {
			throw new NoSuchElementException("Nu s-a gasit produsul!");
		}
	}

}
