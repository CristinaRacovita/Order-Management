package presentation;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import dao.AbstractDAO;
import dao.ProdusDAO;
import model.Client;
import model.Comanda;
import model.Produs;

public class View extends JFrame {

	JButton produsMeniu = new JButton("Editeaza produse ");
	JButton clientMeniu = new JButton("Editeaza clienti ");
	JButton comandaMeniu = new JButton("Fa o comanda, te rog!");

	JFrame meniuFrame = new JFrame();
	JFrame clientFrame = new JFrame();
	JFrame produsFrame = new JFrame();
	JFrame comandaFrame = new JFrame();
	JButton insertClient = new JButton("Insereaza");
	JButton deleteClient = new JButton("Sterge");
	JButton updateClient = new JButton("Update");
	JButton insertProdus = new JButton("Insereaza");
	JButton deleteProdus = new JButton("Sterge");
	JButton updateProdus = new JButton("Update");
	JButton adaugaComanda = new JButton("ADAUGA COMANDA");
	JTable tabelClient = new JTable();
	JTable tabelProduse = new JTable();
	JTable tabelComanda = new JTable();
	JTextField[] textClient;
	JTextField[] textProdus;
	JTextField[] textComanda;
	

	public View() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();

		p1.add(produsMeniu);
		p2.add(clientMeniu);
		p3.add(comandaMeniu);

		p4.add(p1);
		p4.add(p2);
		p4.add(p3);
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		addClientFrame();
		addProdusFrame();
		addComandaFrame();
		meniuFrame.add(p4);
		meniuFrame.setTitle("MENIU PRINCIPAL");
		meniuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		meniuFrame.setSize(400, 500);
		meniuFrame.setVisible(true);

	}

	void addComandaFrame() {
		comandaFrame.setTitle("Comenzi");
		genericFrame(tabelComanda,adaugaComanda,null,null,comandaFrame,Comanda.class);
	}
	
	void addClientFrame() {
		clientFrame.setTitle("Clienti");
		genericFrame(tabelClient, insertClient, deleteClient, updateClient, clientFrame, Client.class);
	}

	void addProdusFrame() {
		produsFrame.setTitle("Produse");
		//genericTableFiller(new ProdusDAO(), tabelProduse, Produs.class);
		genericFrame(tabelProduse, insertProdus, deleteProdus, updateProdus, produsFrame, Produs.class);
	}

	void genericTableFiller(AbstractDAO abstractDAO, JTable tabel, Class c) {
		DefaultTableModel modelTabel = new DefaultTableModel();
		modelTabel.setColumnCount(c.getDeclaredFields().length);
		String[] coloane = new String[c.getDeclaredFields().length];
		int index = 0;
		for (Field f : c.getDeclaredFields()) {
			// System.out.println(f.getName());
			coloane[index++] = f.getName();
		}
		modelTabel.setColumnIdentifiers(coloane);
		int i = 0;
		abstractDAO.findAll();
		while (abstractDAO.date[i] != null) {
			// System.out.println(abstractDAO.date[i]);
			modelTabel.addRow(abstractDAO.date[i]);
			i++;
		}

		tabel.setModel(modelTabel);
	}

	JPanel coloane(Class c) {
		JPanel res = new JPanel();
		// System.out.println(c.getName());
		switch (c.getName()) {
		case "model.Client":
			textClient = new JTextField[c.getDeclaredFields().length];
			break;
		case "model.Produs":
			textProdus = new JTextField[c.getDeclaredFields().length];
			break;
		case "model.Comanda":
			textComanda = new JTextField[c.getDeclaredFields().length];
			break;
		}
		int index = 0;
		res.setLayout(new BoxLayout(res, BoxLayout.Y_AXIS));
		for (Field f : c.getDeclaredFields()) {
			JPanel p = new JPanel();
			JLabel name = new JLabel(f.getName());
			p.add(name);
			switch (c.getName()) {
			case "model.Client":
				textClient[index] = new JTextField(10);
				p.add(textClient[index]);
				break;
			case "model.Produs":
				textProdus[index] = new JTextField(10);
				p.add(textProdus[index]);
				break;
			case "model.Comanda":
				textComanda[index] = new JTextField(10);
				p.add(textComanda[index]);
				break;
			}
			index++;
			res.add(p);
		}

		return res;
	}

	String[] dateFields(Class c) {
		String[] campuri = new String[c.getDeclaredFields().length];
		for (int i = 0; i < c.getDeclaredFields().length; i++) {
			switch (c.getName()) {
			case "model.Client":
				// System.out.println(textClient[i].getText());
				campuri[i] = textClient[i].getText();
				break;
			case "model.Produs":
				campuri[i] = textProdus[i].getText();
				break;
			case "model.Comanda":
				campuri[i] = textComanda[i].getText();
				break;
			}
		}

		return campuri;
	}

	void genericFrame(JTable table, JButton insert, JButton delete, JButton update, JFrame frame, Class c) {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setRowHeight(20);
		JPanel p4 = new JPanel();

		p1.add(scroll);
		if (insert != null) {
			p2.add(insert);
		}
		if(delete != null) {
		p3.add(delete);
		}
		if(update != null) {
		p4.add(update);
		}

		JPanel p5 = new JPanel();
		p5.add(p2);
		p5.add(p3);
		p5.add(p4);
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));

		JPanel p7 = new JPanel();

		p7.add(coloane(c));
		p7.add(p5);
		p7.setLayout(new BoxLayout(p7, BoxLayout.X_AXIS));

		JPanel p6 = new JPanel();
		p6.add(p1);
		p6.add(p7);
		p6.setLayout(new BoxLayout(p6, BoxLayout.Y_AXIS));

		frame.add(p6);
		frame.setSize(600, 700);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	void addMainClientListener(ActionListener listener) {
		clientMeniu.addActionListener(listener);
	}

	void addComandaListener(ActionListener listener) {
		adaugaComanda.addActionListener(listener);
	}

	void addMainProdusListener(ActionListener listener) {
		produsMeniu.addActionListener(listener);
	}

	void addMainComandaListener(ActionListener listener) {
		comandaMeniu.addActionListener(listener);
	}

	void addInsertClientListener(ActionListener listener) {
		insertClient.addActionListener(listener);
	}

	void addDeleteClientListener(ActionListener listener) {
		deleteClient.addActionListener(listener);
	}

	void addUpdateClientListener(ActionListener listener) {
		updateClient.addActionListener(listener);
	}

	void addInsertProdusListener(ActionListener listener) {
		insertProdus.addActionListener(listener);
	}

	void addDeleteProdusListener(ActionListener listener) {
		deleteProdus.addActionListener(listener);
	}

	void addUpdateProdusListener(ActionListener listener) {
		updateProdus.addActionListener(listener);
	}
}
