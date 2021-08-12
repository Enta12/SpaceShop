package fr.spaceShop.modele.humans.actions;

import java.io.Serializable;

public class PersonalInformations implements Serializable{
	private int picture;;
	private String mail;
	
	
	public PersonalInformations(String name){
		mail = name + ((int) (Math.random() * (60))+ 9158) + ".spacefederation.space";
		picture = (int) (Math.random() * 20);
	}
	
	public PersonalInformations(String name, int picture){
		mail = name + ((int) (Math.random() * (60))+ 9158) + ".spacefederation.space";
		this.picture = picture;
	}
	
	public String getMail(){
		return mail;
	}
	
	public int getPicture(){
		return picture;
	}
}
