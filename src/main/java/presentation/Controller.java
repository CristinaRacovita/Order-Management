package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import bll.ClientBLL;
import bll.ComandaBLL;
import bll.ProdusBLL;
import dao.ClientDAO;
import dao.ComandaDAO;
import dao.ProdusDAO;
import model.Client;
import model.Comanda;
import model.Produs;

public class Controller {

	View view;

	public Controller(View v) {
		this.view = v;

		view.addMainClientListener(new AddMainClientListener());
		view.addMainProdusListener(new AddMainProdusListener());
		view.addMainComandaListener(new AddMainComandaListener());
		view.addInsertClientListener(new AddInsertClientListener());
		view.addDeleteClientListener(new AddDeleteClientListener());
		view.addUpdateClientListener(new AddUpdateClientListener());
		view.addInsertProdusListener(new AddInsertProdusListener());
		view.addDeleteProdusListener(new AddDeleteProdusListener());
		view.addUpdateProdusListener(new AddUpdateProdusListener());
		view.addComandaListener(new AddComandaListener()); // insert

	}
	
	public class AddComandaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] campuriComanda = view.dateFields(Comanda.class);
			ComandaBLL comanda = new ComandaBLL();
			ProdusBLL prod = new ProdusBLL();
            try {
            	//System.out.println(Integer.parseInt(campuriComanda[4]));
            	Comanda c = new Comanda(Integer.parseInt(campuriComanda[0]),campuriComanda[1],campuriComanda[2],Integer.parseInt(campuriComanda[3]),Integer.parseInt(campuriComanda[4]),Integer.parseInt(campuriComanda[5]));//id,numeClient,numeProdus,cantitate,idProdus,pret
              // System.out.println("lala");
            	Produs p = prod.findById(Integer.parseInt(campuriComanda[4]));
                //System.out.println(p.getStoc() + " " + c.getCantitate() );
                if(p.getStoc() >= c.getCantitate()) {
                   
                	comanda.insert(c);
                	p.setStoc(p.getStoc()-c.getCantitate());
                	prod.update(p);
                	
                	PrintWriter writer = new PrintWriter("Chitanta.txt","UTF-8");
                	writer.println("---------------------");
                	writer.println("Nume client : "+c.getNumeClient());
                	writer.println("Produs cumparat : "+c.getNumeProdus());
                	writer.println("Cantitate : "+c.getCantitate());
                	writer.println("Pret : "+c.getPret());
                	writer.println("Multumim ca ati cumparat de la noi!!!");
                	writer.println("---------------------");
                	writer.close();
                	
                }
                else {
                	//System.out.println("lalala");
                	throw new Exception();
                }
                view.genericTableFiller(new ComandaDAO(), view.tabelComanda, Comanda.class);
				view.tabelComanda.repaint();
				view.tabelComanda.revalidate();
            }catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Ceva nu merge bine :(", "EROARE!", JOptionPane.ERROR_MESSAGE);
            }
		}

	}

	public class AddMainComandaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.comandaFrame.setVisible(true);
			view.genericTableFiller(new ComandaDAO(), view.tabelComanda, Comanda.class);
		}

	}

	public class AddMainProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.produsFrame.setVisible(true);
			view.genericTableFiller(new ProdusDAO(), view.tabelProduse, Produs.class);
		}

	}

	public class AddMainClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.clientFrame.setVisible(true);
			view.genericTableFiller(new ClientDAO(), view.tabelClient, Client.class);

		}

	}

	public class AddInsertClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] fields = view.dateFields(Client.class);
			ClientBLL c = new ClientBLL();
			/*
			 * for(String s : fields) { System.out.println(s); }
			 */
			try {
				Client client = new Client(Integer.parseInt(fields[0]), fields[1], fields[2],
						Integer.parseInt(fields[3]), fields[4]); // id,nume,adresa,varsta,mail
				// System.out.println(client.getId()+" "+client.getNume()+"
				// "+client.getVarsta());
				c.insert(client);
				view.genericTableFiller(new ClientDAO(), view.tabelClient, Client.class);
				view.tabelClient.repaint();
				view.tabelClient.revalidate();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ceva nu merge bine :(", "EROARE!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public class AddDeleteClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] fields = view.dateFields(Client.class);
			ClientBLL c = new ClientBLL();

			try {
				int id = c.delete(Integer.parseInt(fields[0]));
				// System.out.println(client.getId()+" "+client.getNume()+"
				// "+client.getVarsta());
				view.genericTableFiller(new ClientDAO(), view.tabelClient, Client.class);
				view.tabelClient.repaint();
				view.tabelClient.revalidate();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ceva nu merge bine :(", "EROARE!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public class AddUpdateClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] fields = view.dateFields(Client.class);
			ClientBLL c = new ClientBLL();

			try {
				Client client = new Client(Integer.parseInt(fields[0]), fields[1], fields[2],
						Integer.parseInt(fields[3]), fields[4]); // id,nume,adresa,varsta,mail
				c.update(client);
				// System.out.println(client.getId()+" "+client.getNume()+"
				// "+client.getVarsta());
				view.genericTableFiller(new ClientDAO(), view.tabelClient, Client.class);
				view.tabelClient.repaint();
				view.tabelClient.revalidate();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ceva nu merge bine :(", "EROARE!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public class AddInsertProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] fields = view.dateFields(Produs.class);
			ProdusBLL p = new ProdusBLL();
			/*
			 * for(String s : fields) { System.out.println(s); }
			 */
			try {
				Produs prod = new Produs(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2])); // id,nume,stoc
				// System.out.println(client.getId()+" "+client.getNume()+"
				// "+client.getVarsta());
				p.insert(prod);
				view.genericTableFiller(new ProdusDAO(), view.tabelProduse, Produs.class);
				view.tabelClient.repaint();
				view.tabelClient.revalidate();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ceva nu merge bine :(", "EROARE!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public class AddDeleteProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] fields = view.dateFields(Produs.class);
			ProdusBLL p = new ProdusBLL();
			/*
			 * for(String s : fields) { System.out.println(s); }
			 */
			try {
				// Produs prod = new
				// Produs(Integer.parseInt(fields[0]),fields[1],Integer.parseInt(fields[2]));
				// //id,nume,stoc
				// System.out.println(client.getId()+" "+client.getNume()+"
				// "+client.getVarsta());
				int id = p.delete(Integer.parseInt(fields[0]));
				view.genericTableFiller(new ProdusDAO(), view.tabelProduse, Produs.class);
				view.tabelClient.repaint();
				view.tabelClient.revalidate();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ceva nu merge bine :(", "EROARE!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public class AddUpdateProdusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] fields = view.dateFields(Produs.class);
			ProdusBLL p = new ProdusBLL();
			/*
			 * for(String s : fields) { System.out.println(s); }
			 */
			try {
				Produs prod = new Produs(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2])); // id,nume,stoc
				// System.out.println(client.getId()+" "+client.getNume()+"
				// "+client.getVarsta());
				p.update(prod);
				view.genericTableFiller(new ProdusDAO(), view.tabelProduse, Produs.class);
				view.tabelClient.repaint();
				view.tabelClient.revalidate();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ceva nu merge bine :(", "EROARE!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
