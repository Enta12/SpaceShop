package fr.spaceShop.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.spaceShop.controler.AdmnistratorCustomerListener;
import fr.spaceShop.controler.AdmnistratorWorkerListener;
import fr.spaceShop.controler.Controler;
import fr.spaceShop.controler.GameLoadListener;
import fr.spaceShop.modele.globalInformations.Date;
import fr.spaceShop.modele.humans.actions.Customer;
import fr.spaceShop.modele.humans.actions.Human;
import fr.spaceShop.modele.humans.actions.Worker;

public class GameLoad extends JPanel{
	private JPanel content = new JPanel();
	private JPanel customersField, workersField;
	private JLabel date, moneyLabel, nbSellersLabel,nbGuardsLabel, nbfaithFullCustomerLabel;
	private int width, height;
	private JButton stats, menu, nextWeek, nextDay, products;
	
	//list for workers and customers
	private Font informationsFont;
	private List<JButton> workersButtons = new ArrayList();
	private List<JPanel>  workersPanels = new ArrayList();
	private List<Integer>  workerID = new ArrayList();
	private List<JDialog> workerAdmnistrator = new ArrayList();
	private List<JDialog> customerAdmnistrator = new ArrayList();
	private List<Integer>  customerID = new ArrayList();
	private List<JButton> customersButtons = new ArrayList();
	private List<JPanel> customersPanels = new ArrayList();
	private AdmnistratorWorkerListener admnistratorWorkerListener = new AdmnistratorWorkerListener(workersButtons, workerAdmnistrator, workerID);
	private AdmnistratorCustomerListener admnistratorCustomerListener = new AdmnistratorCustomerListener(customersButtons, customerAdmnistrator);
	
	public GameLoad(int width, int height, int scenario, String shopName){
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setPreferredSize(new Dimension(width, height));
		this.width = width;
		this.height = height;
		informationsFont = new Font("Courier New", Font.BOLD, (int)( width* 0.015625));
		
		///////////////////////////////////////////header////////////////////////////////////////////////
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
		header.setPreferredSize(new Dimension(width, 250));
		header.setBackground(Color.DARK_GRAY);
		this.add(header);
		//Scenario
		JLabel scenarioLogo= new JLabel( new ImageIcon( "files/img/logo/logo" + scenario +".png")); 
		scenarioLogo.setPreferredSize(new Dimension(200, 200));
		header.add(scenarioLogo);
		//shop Name
		JLabel shopNameLabel= new JLabel(shopName); 
		shopNameLabel.setForeground(Color.white);
		shopNameLabel.setFont(new Font("Courier New", Font.BOLD, 40));
		shopNameLabel.setPreferredSize(new Dimension(width-300-500, 200));
		
		header.add(shopNameLabel);
		//date
		date = new JLabel(Date.currentDate + ""); 
		date.setPreferredSize(new Dimension(400, 200));
		date.setFont(new Font("Courier New", Font.BOLD, 50));
		date.setForeground(Color.white);
		header.add(date);
		
		///////////////////////////////////////////content////////////////////////////////////////////////
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		content.setPreferredSize(new Dimension(width, height-500));
		content.setBackground(Color.LIGHT_GRAY);
		this.add(content);
		//title of worker and //
		JLabel customerLabel = new JLabel();
		contentTitle(customerLabel, "Client(s) fidèle(s)");
		content.add(customerLabel);
		customerLabel.setPreferredSize(new Dimension((width-80-150)/3, 30));
		JLabel workerLabel = new JLabel();
		contentTitle(workerLabel, "Employé(s)");
		content.add(workerLabel);
		workerLabel.setPreferredSize(new Dimension((width-80-150)/3, 30));
		JLabel informationsLabel = new JLabel();
		contentTitle(informationsLabel, "Informations générales");
		informationsLabel.setPreferredSize(new Dimension((width-80+300)/3, 30));
		content.add(informationsLabel);
		
		//faithFullCustomer
		customersField = new JPanel();
		customersField.setBackground(Color.DARK_GRAY);
		JScrollPane CustomersScroll = new JScrollPane(customersField);
		CustomersScroll.setPreferredSize(new Dimension((width-80-150)/3, height-590));
		CustomersScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		CustomersScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		content.add(CustomersScroll);
		//worker
		workersField = new JPanel();
		workersField.setBackground(Color.DARK_GRAY);
		JScrollPane WorkersSroll = new JScrollPane(workersField);
	    WorkersSroll.setPreferredSize(new Dimension((width-80-150)/3, height-590));
		WorkersSroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		WorkersSroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		content.add(WorkersSroll);
		//informations//
		JPanel informations = new JPanel();
		informations.setLayout(new FlowLayout(FlowLayout.CENTER, 10,0));
		informations.setPreferredSize(new Dimension((width-80+300)/3, height-590));
		informations.setBackground(Color.DARK_GRAY);
		content.add(informations);
		//money
		JLabel moneyTitle = new JLabel("Argent du magasin : ");
		tiltleInformationFormate(moneyTitle);
		informations.add(moneyTitle);
		moneyLabel = new JLabel(Controler.getMoney() + "");
		contentInformationFormate(moneyLabel);
		informations.add(moneyLabel);
		//dateStart
		JLabel dateStartTitle = new JLabel("Date du début : ");
		tiltleInformationFormate(dateStartTitle);
		informations.add(dateStartTitle);
		JLabel dateStartLabel;
		if(scenario == 1){
			dateStartLabel = new JLabel("12/12/9056");
		}
		else if (scenario == 2){
			dateStartLabel = new JLabel("12/12/8056");
		}
		else {
			dateStartLabel = new JLabel("12/12/9812");
		}
		contentInformationFormate(dateStartLabel);
		informations.add(dateStartLabel);
		//nbGuards
		JLabel nbGuardsTitle = new JLabel("Nombre de gardes : ");
		tiltleInformationFormate(nbGuardsTitle);
		informations.add(nbGuardsTitle);
		nbGuardsLabel = new JLabel(Controler.getGuardsNb() + "");
		contentInformationFormate(nbGuardsLabel);
		informations.add(nbGuardsLabel);
		//nbSeller
		JLabel nbSellerTitle = new JLabel("Nombre de vendeurs : ");
		tiltleInformationFormate(nbSellerTitle);
		informations.add(nbSellerTitle);
		nbSellersLabel = new JLabel(Controler.getSellersNb() + "");
		contentInformationFormate(nbSellersLabel);
		informations.add(nbSellersLabel);
		//nbfaithFullCustomer
		JLabel nbfaithFullCustomerTitle = new JLabel("Clients fidèles : ");
		tiltleInformationFormate(nbfaithFullCustomerTitle);
		informations.add(nbfaithFullCustomerTitle);
		nbfaithFullCustomerLabel = new JLabel(0 + "");
		contentInformationFormate(nbfaithFullCustomerLabel);
		informations.add(nbfaithFullCustomerLabel);
		//goal
		JLabel goalTitle = new JLabel("Objectif : ");
		tiltleInformationFormate(goalTitle);
		informations.add(goalTitle);
		JLabel goalLabel = new JLabel();
		if (scenario == 1){
			goalLabel.setText("<html>10 Gardes<br>20 000 spaceuros</html>");
		}
		else if (scenario == 2){
			goalLabel.setText("<html>100 000 spaceuros</html>");
		}
		else {
			goalLabel.setText("<html>20 vendeurs<br>30 clients fidèles<br>100 000 spaceuros</html>");
		}
		contentInformationFormate(goalLabel);
		informations.add(goalLabel);
		
		///////////////////////////////////////////buttons////////////////////////////////////////////////
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		buttons.setPreferredSize(new Dimension(width, 250));
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, (width-800)/8, 50));
		buttons.setBackground(Color.DARK_GRAY);
		menu = new JButton("Menu");
		configButton(buttons, menu);
		buttons.add(menu);
		stats = new JButton("Statistiques");
		configButton(buttons, stats);
		buttons.add(stats);
		nextDay = new JButton("Jour suivant");
		configButton(buttons, nextDay);
		buttons.add(nextDay);
		nextWeek =  new JButton("Semaine suivante");
		configButton(buttons, nextWeek);
		buttons.add(nextWeek);
		products = new JButton("Produits");
		configButton(buttons, products);
		buttons.add(products);
		GameLoadListener buttonsListener = new GameLoadListener(menu, stats, nextDay, nextWeek, this);
		menu.addActionListener(buttonsListener);
		stats.addActionListener(buttonsListener);
		nextDay.addActionListener(buttonsListener);
		nextWeek.addActionListener(buttonsListener);
		products.addActionListener(buttonsListener);
		this.add(buttons);
		
	}
	
	private void configButton(JPanel buttons, JButton button){
		button.setFont(new Font("Courier New", Font.BOLD, 12));
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.black);
		button.setPreferredSize(new Dimension(175, 100));

	}
	
	private void contentTitle(JLabel titleLabel, String title){
		titleLabel.setText(title);
		titleLabel.setForeground(Color.black);
		titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		content.add(titleLabel);
	}
	
	private void tiltleInformationFormate(JLabel informationLabel){
		informationLabel.setFont(new Font("Courier New", Font.BOLD, (int)( width* 0.01041667)));
		informationLabel.setPreferredSize(new Dimension((((width-80)/3 - 20)/5)*3, (height-590)/6));
		informationsFormate(informationLabel);
	}
	
	private void contentInformationFormate(JLabel informationLabel){
		informationLabel.setFont(new Font("Courier New", Font.BOLD, (int)( width* 0.01041667)));
		informationLabel.setPreferredSize(new Dimension((((width-80)/3 - 20)/5)*2, (height-590)/6));
		informationsFormate(informationLabel);
	}
	
	private void informationsFormate(JLabel informationLabel){
		informationLabel.setForeground(Color.white);
		informationLabel.setHorizontalAlignment(JLabel.CENTER);
		informationLabel.setVerticalAlignment(JLabel.CENTER);
		content.add(informationLabel);
	}
	
	public void addWorker(Worker worker){
		workersField.setPreferredSize(new Dimension((width-80-150)/3, 35*workersButtons.size()));
		//id
		workerID.add(worker.getId());
		workerAdmnistrator.add(Controler.adminWorker(worker.getId()));

		//seller panel
		workersPanels.add(new JPanel());
		workersPanels.get(workersPanels.size()-1).setBackground(Color.DARK_GRAY);
	    //JLabel
	    JLabel workerLabel = new JLabel(worker.getJobFR() + " : "+ worker);
	    workerLabel.setForeground(Color.white);
	    workerLabel.setFont(informationsFont);
	    workersPanels.get(workersPanels.size() -1).add(workerLabel);
	    workerLabel.setPreferredSize(new Dimension((width-80-150)/3 -120, 35));
	    
	    //button
	    workersButtons.add(new JButton(new ImageIcon( "files/img/administrator.jpg")));
	    workersPanels.get(workersPanels.size() -1).add(workersButtons.get(workersButtons.size()-1));
	    workersButtons.get(workersButtons.size()-1).setPreferredSize(new Dimension(30, 30));
	    workersButtons.get(workersButtons.size()-1).addActionListener(admnistratorWorkerListener);
	    
	    workersField.add(worker.getId() + "",workersPanels.get(workersPanels.size()-1));
	}
	
	public void removeWorkers(Human worker){
		for(int i = 0; i< workerID.size(); i++)if(worker.getId() == workerID.get(i)){
			workerID.remove(i);
			workerAdmnistrator.remove(i);
			workersButtons.remove(i);
			workersPanels.remove(i);
			workersField.remove(i);
			workersField.repaint();
			break;
		}
	}
	
	public void removeFaithFullCustomer(Human customer){
		for(int i = 0; i< customerID.size(); i++)if(customer.getId() == customerID.get(i)){
			customerID.remove(i);
			customerAdmnistrator.remove(i);
			customersButtons.remove(i);
			customersPanels.remove(i);
			customersField.remove(i);
			customersField.repaint();
			break;
		}
	}
	
	public void addFaithfullCustomer(Customer customer){
		customersField.setPreferredSize(new Dimension((width-80-150)/3, 35*workersButtons.size()));
		//id
		customerAdmnistrator.add(Controler.adminCustomer(customer.getId()));
		customerID.add(customer.getId());
		//seller panel
		customersPanels.add(new JPanel());
		customersPanels.get(customersPanels.size()-1).setBackground(Color.DARK_GRAY);
	    //JLabel
	    JLabel customerLabel = new JLabel("Client : "+ customer);
	    customerLabel.setForeground(Color.white);
	    customerLabel.setFont(informationsFont);
	    customersPanels.get(customersPanels.size() -1).add(customerLabel);
	    customerLabel.setPreferredSize(new Dimension((width-80-150)/3 -120, 35));
	    
	    //button
	    customersButtons.add(new JButton(new ImageIcon( "files/img/administrator.jpg")));
	    customersPanels.get(customersPanels.size() -1).add(customersButtons.get(customersButtons.size()-1));
	    customersButtons.get(customersButtons.size()-1).setPreferredSize(new Dimension(30, 30));
	    customersField.add(customer.getId() + "",customersPanels.get(customersPanels.size()-1));
	    customersButtons.get(customersButtons.size()-1).addActionListener(admnistratorCustomerListener);
	}
	
	public void actualise(){
		date.setText(Date.currentDate.toString());
		moneyLabel.setText(Controler.getMoney() + "");
		nbSellersLabel.setText(Controler.getGuardsNb() + "");
		nbGuardsLabel.setText(Controler.getSellersNb() + "");
		nbfaithFullCustomerLabel.setText(customerID.size() + "");
	}
	
}

