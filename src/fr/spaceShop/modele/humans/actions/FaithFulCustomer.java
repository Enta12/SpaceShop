package fr.spaceShop.modele.humans.actions;

import java.util.ArrayList;
import java.util.List;

import fr.spaceShop.modele.globalInformations.Date;
import fr.spaceShop.modele.products.Purchase;

public class FaithFulCustomer extends Customer{
	private PersonalInformations personalInformations;
	private Date fidelizeDate;
	private List<Purchase> purchase = new ArrayList();
	private static FaithFulCustomer legendaryFaithFulCustomer[] = new FaithFulCustomer[10];
	private static boolean customerLgExist[] = new boolean[5];
	
	
	public FaithFulCustomer(Customer customer){
		super(customer);
		job = "FaithFulCustomer";
		jobFR = "Client fidèle";
		fidelizeDate = Date.currentDate;
		personalInformations = new PersonalInformations(name);
	}
	
	public FaithFulCustomer(Customer customer, int picture){
		super(customer);
		job = "FaithFulCustomer";
		jobFR = "Client fidèle";
		fidelizeDate = Date.currentDate;
		personalInformations = new PersonalInformations(name, picture);
	}
	
	public static void initLg(){
		legendaryFaithFulCustomer[0] = new FaithFulCustomer(new Customer("Evil Night", 100, 10, 100, 100, 10, 100), 20);
		legendaryFaithFulCustomer[1] = new FaithFulCustomer(new Customer("Beauzieu", 0, 0, 0, 0, 0, 100), 21);
		legendaryFaithFulCustomer[2] = new FaithFulCustomer(new Customer("Amsterdamois", 100, 10, 10, 10, 10, 100), 22);
		legendaryFaithFulCustomer[3] = new FaithFulCustomer(new Customer("Rapido", 100, 10, 10, 100, 10, 100), 23);
		legendaryFaithFulCustomer[4] = new FaithFulCustomer(new Customer("Dark Dark", 100, 10, 100, 10, 10, 100), 24);
	}
	
	public static void setCustomerLgExist(){
		customerLgExist[0] = true;
		customerLgExist[1] = true;
		customerLgExist[2] = true;
		customerLgExist[3] = true;
		customerLgExist[4] = true;
	}
	
	public static FaithFulCustomer getCustomerLg(int i){
		if (customerLgExist[i]){
			customerLgExist[i] = false;
			return legendaryFaithFulCustomer[i];
		}
		else return null;
	}
	
	public static boolean[] getCustomerLgExist(){
		return customerLgExist;
	}
	
	public static void setCustomerLgExist(boolean arrayExistLg[]){
		customerLgExist = arrayExistLg;
	}
	

	public String getMail() {
		return personalInformations.getMail();
	}
	
	public Date getFidelizeDate(){
		return fidelizeDate;
	}

	public int getPicture() {
		return personalInformations.getPicture();
	}
}