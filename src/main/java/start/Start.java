package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.Controller;
import presentation.View;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		//Client c = new Client(10,"dorel","dorel",21,"cri@yahoo.com");
		//Client ci = new Client(1,"ginger","super",20,"super@gmail.com");
		//ClientBLL cBLL = new ClientBLL();
		
		//List<Client> c1 =cBLL.findall();
		//Client cl = cBLL.insert(ci);
		/*
		for(Client client : c1) {
			System.out.println(client.getNume());
		}
		*/
		/*for(Client client : c1) {
			System.out.println(client.getId_client() + " " + client.getNume());
		}*/
		
		//Client rez = cBLL.findClientById(2);
		
		//System.out.println("id : " +cl.getId()+ " nume: "+cl.getNume());
		
		View v = new View();
		Controller c = new Controller(v);
		//v.setVisible(true);
	}
	
	

}
