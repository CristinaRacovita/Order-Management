package model;

public class Client {

	private int id;
	private String nume;
	private String adresa;
	private int varsta;
	private String email;

	public Client() {
		
	}
	
	public Client (int id,String nume,String add,int age,String mail) {
		this.id=id;
		this.nume=nume;
		this.adresa=add;
		this.varsta=age;
		this.email=mail;
	}
	public int getId() {
		return id;
	}

	public void setId(int id_client) {
		this.id = id_client;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getVarsta() {
		return varsta;
	}

	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
