package fr.spaceShop.gui;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.spaceShop.controler.Controler;
import fr.spaceShop.modele.humans.actions.Customer;
import fr.spaceShop.modele.humans.actions.FaithFulCustomer;
import fr.spaceShop.modele.humans.actions.Human;
import fr.spaceShop.modele.humans.actions.PDG;
import fr.spaceShop.modele.humans.actions.Worker;
import fr.spaceShop.modele.products.Purchase;
import fr.spaceShop.modele.products.PurchaseShop;
import fr.spaceShop.modele.products.Stock;
import fr.spaceShop.modele.products.WholeSealer;


public class Window  extends JFrame{
	private int width;
	private int height;
	private Menu menu;
	private GameLoad gameLoad;
	private ProductManager productManager;
	private PdgAdmnistrator pdgAdmnistrator;
	private Stats stats;
	private Event events[] = {new Event(this, "Un avion s'est crash� !", "Oups, il semblerait que le pilote de cet avion n'�tait pas tr�s dou�, rien sur des milliers de km et il a r�ussi à se prendre votre station ....<br>" +
			"Bon les d�gats sont minimes ... OH MON DIEU IL EST PLEIN D'OR !! On va bien y gagner 500 spaceuros", 0),
			new Event(this,"Des hamsters voleurs !", "Des hamsters super-habiles, viennent voler vos stocks.. Ils sont tr�s rapides et embarquent tout ce qu'ils trouvent ...<br>" +
					"Ils voleront tout jusqu'à ce que quelqu'un les arr�tent ! ", 1),
			new Event(this,"Oulala le panneau droit est parti !","Oups, il semblerait que le panneau solaire droit ai lach� ... S�rement à cause d'un d�brit ou d'un petit ast�roide.. Il va falloir r�parer �a ! <br>" +
					"Les d�gats sont assez important �a va couter assez ch�re, le cout de la r�paration doit �tre de 500 spaceuros",  2), 
			new Event(this,"Oulala un virus informatique !", "Oups, il semblerait que quelqu'un ai mis un cd infect� dans un ordinateur de votre magasin, �a sent pas tr�s bon les icones de vos ordinateurs disparaissent toutes seules ....<br>" +
					"Aie aie aie ... On va pouvoir arranger �a mais les programmes à vendre ont tous disparu....", 3), 
			new Event(this,"Boom une grosse explosion !", "Bien que l'explosion f�t loin et qu'on n'a rien entendu parceque le son ne se d�place pas dans l'espace, certaines ondes si et elles sont arriv�es... Et h�las elle d�truisent les t�l�phones...<br>" +
					"Tout le stock de t�l�phone est d�truit, c'est assez dommage ! ", 4), 
			new Event(this,"Ninja !", "Ninja ! Ninja ! ... Ninja ! ... Ninja ! Ninja ! Oulala un ninja est entr� il veut tuer vos employ� !! l'affrontement risque de d�g�n�r� ...<br>" +
					"Ils se pwindowlacent face à face et se pr�parent au combat... ",5), 
			new Event(this,"Une maladie dangereuse !","Oulala ! Oulalalala ! Un de vos employ�s est venu ce matin, tout vert, voir m�me un peu violet... Il est parti aux toilettes, et vous avez agit...<br>" +
					"Vous avez ouvert le SAS, question de s�curit�, pouf plus d'employ�s ! ", 6),
			new Event(this,"TAUPE !!!", "OH NON !! C'EST MONSTRUEUX !! Une taupe carnivore vient nous attaquer !! Elle prend l'un de vos employ�s en duel ...<br>" +
					"Ils se placent face à face et se pr�parent au combat... ", 7), 
			new Event(this, "Au feu !", "Mince ! Mince ! Mince ! Le feu s'est propag� dans l'aile gauche ! Dans le quartier des employ�s, br�lant le babyfoot et la machine à caf� ... <br>" + 
					"Pour racheter le mat�riel, �a va co�ter 50 spaceuros...",8), 
			new Event(this,"Une pomme �trange !", "Un de vos employ�s a �t� vu manger une pomme v�rr� à la cantine, il est devenu vert, puis jaune et un peu rouge... <br>" +
					"Bizarrement il a retrouv� sa couleur normal mais a l'air un peu chang�...", 9)};
	public Window() { 
		this.setUndecorated(true);
		this.setAlwaysOnTop(false);
		this.setTitle("Space Shop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = (int)Toolkit.getDefaultToolkit().getScreenSize().width;
		height = (int)Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(width, height);
        this.setVisible(true);
        this.add(menu = new Menu(width, height));
        this.validate();
	}
	
	public void backToMenu(){
		this.remove(gameLoad);
		this.add(menu = new Menu(width, height));
		this.validate();
	}
	
	public void gameScreen(int scenario, String shopName){
		this.remove(menu);
		this.add(gameLoad = new GameLoad(width, height, scenario, shopName));
		this.validate();
		stats = new Stats(this);
	}
	
	public void addElement(Purchase purchase){
		stats.addElement(purchase);
  	}
	
	
	public void actualise(){
		gameLoad.actualise();
	}
	
	public void reloadProductManager(Stock[] stocks, WholeSealer[] wholeSealers){
		productManager= new ProductManager(this, stocks, wholeSealers);
		productManager.setVisible(true);
	}
	
	public void addWorker(Worker worker){
		gameLoad.addWorker(worker);
	}

	public void addFaithfullCustomer(Customer customer) {
		gameLoad.addFaithfullCustomer(customer);
		
	}

	public void alertSteal(Human steal, String msg) {
		boolean isWorker = !steal.getJob().equals("Customer");
		String title;
		if(isWorker){
			title = "Un employ� vole !";
			msg += ", voulez vous le virer sans prime de d�part ?";
		}
		else{
			title = "Un client est un voleur !";
			msg += ", voulez vous l'ex�cuter ?";
		}
		JOptionPane alert;
		alert = new JOptionPane();
		int option = alert.showConfirmDialog(null, msg, title, JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		if(option != JOptionPane.NO_OPTION && 
		   option != JOptionPane.CANCEL_OPTION && 
		   option != JOptionPane.CLOSED_OPTION){
			if(isWorker){
				Controler.fire(steal);
			}
			else Controler.putToDeath( (Customer) steal);
		}
		
	}
	
	public void candidate(Worker candidate, Worker pdg) {
		Candidate askCandidate = new Candidate(this, candidate, pdg);
		
	}
	
	public void msg(String text, String title){
		JOptionPane alert;
		alert = new JOptionPane();
		int option = alert.showConfirmDialog(null, text, title, JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	public JDialog adminWorker(Worker worker) {
		return new WorkerAdmnistrator(this, worker);
		
	}
	
	public JDialog adminWorker(PDG pdg) {
		pdgAdmnistrator = new PdgAdmnistrator(this, pdg);
		return pdgAdmnistrator;
		
	}
	
	public void removeWorkers(Human worker) {
		gameLoad.removeWorkers(worker);
	}
	
	public void removeFaithFullCustomer(Customer customer){
		gameLoad.removeFaithFullCustomer(customer);
	}

	public JDialog adminCustomer(FaithFulCustomer customer) {
		return new CustomerAdministrator(this, customer);
	}

	public void updatePDGAdministrator(PDG pdg) {
		pdgAdmnistrator = new PdgAdmnistrator(this, pdg);
		pdgAdmnistrator.setVisible(true);
		
	}

	public void addElement(PurchaseShop purchaseShop) {
		stats.addElement(purchaseShop);
	}
	
	public void actualiseStatMoneyEarn(List<Integer> statMoneyEarn){
		stats.actualiseStatMoneyEarn(statMoneyEarn);
	}

	public void displayStats() {
		stats.setVisible(true);
		
	}

	public void gameOver(int scenario, int i) {
		GameOver gameOver = new GameOver(this, scenario, i);
		backToMenu();
	}

	public void initProduct(Stock[] stocks, WholeSealer[] wholeSealers) {
		productManager = new ProductManager(this, stocks, wholeSealers);
		
	}

	public void welcomingWindow() {
		WelcomingNewGame WelcomingNewGame = new WelcomingNewGame(this);
	}

	public void win(int scenario, int i) {
		Win win = new Win(this, scenario, i);
		
	}
	
	public void showEvent(int eventNb){
		events[eventNb].setVisible(true);
	}

}
