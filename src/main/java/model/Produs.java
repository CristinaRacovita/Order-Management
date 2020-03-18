package model;

public class Produs {
	private int id;
	private String denumire;
	private int stoc;

	public Produs(){
		
	}
	
	public Produs(int id,String nume,int stoc) {
		this.id=id;
		this.denumire=nume;
		this.stoc=stoc;
	}
	public int getId() {
		return id;
	}

	public void setId(int idProdus) {
		this.id = idProdus;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public int getStoc() {
		return stoc;
	}

	public void setStoc(int stoc) {
		this.stoc = stoc;
	}

}
