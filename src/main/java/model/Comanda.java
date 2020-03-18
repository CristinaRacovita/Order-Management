package model;

public class Comanda {

	private int id;
	private String numeClient;
	private String numeProdus;
	private int cantitate;
	private int idProdus;
	private int pret;

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}


	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public Comanda(int id, String nume, String produs, int cant,int idClient,int pret) {
		this.id = id;
		this.numeClient = nume;
		this.numeProdus = produs;
		this.cantitate = cant;
		this.idProdus=idClient;
		this.pret=pret;
	}

	public int getId() {
		return id;
	}

	public void setId(int idComanda) {
		this.id = idComanda;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	public String getNumeProdus() {
		return numeProdus;
	}

	public void setNumeProdus(String numeProdus) {
		this.numeProdus = numeProdus;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

}
