package fr.spaceShop.modele.humans.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public abstract class Human implements Steal, Serializable{
	protected String name;
	private static int nbHumans =0;
	protected int id;
	
	//stats
	protected int criminal;
	protected int strenght;
	protected int charism;
	protected int agility;
	protected int vision;
	protected String job =null;
	protected String jobFR =null;
	private static String names[] = null;
	
	
	public Human(){
		name = getRandomName();
		id = nbHumans++;
		criminal = (int) (Math.random() * (20));
		charism = (int) (Math.random() * (20));
		strenght = (int) (Math.random() * (20));
		agility = (int) (Math.random() * (20));
		vision = (int) (Math.random() * (20));
	}
	
	public Human(Human human){
		name = human.getName();
		id = human.getId();
		criminal = human.getCriminal();
		charism = human.getCharism();
		strenght = human.getStrenght();
		agility = human.getAgility();
		vision = human.getVision();
	}
	
	public Human(String name) {
		this.name = name;
		id = nbHumans++;
		criminal = (int) (Math.random() * (20));
		charism = (int) (Math.random() * (20));
		strenght = (int) (Math.random() * (20));
		agility = (int) (Math.random() * (20));
		vision = (int) (Math.random() * (20));
	}
	
	public Human(String name, int criminal, int charism, int strenght, int agility, int vision) {
		this.name = name;
		id = nbHumans++;
		this.criminal = criminal;
		this.charism = charism;
		this.strenght = strenght;
		this.agility = agility;
		this.vision = vision;
	}

	//accessor
	public int getCriminal(){
		return criminal;
	}
	
	public int getCharism(){
		return charism;
	}
	
	public int getStrenght(){
		return strenght;
	}
	
	public int getAgility(){
		return agility;
	}
	
	public int getVision(){
		return vision;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	//return winner 
	public static boolean confrontation(int stat0, int stat1){
		if (((int) (Math.random() * ( 20 - 0 )))< stat0 - (stat1-10)) return true; 
		else return false;
	}
	
	public String toString(){
		return name; 
	}

	public boolean wantSteal() {
		if (((int)Math.random()* (20 - 0)) < criminal) return true;
		else return false;
	}

	
	public abstract void trySteal();
	
	public static void init(){
		ObjectInputStream ois;
		try {
    		ois = new ObjectInputStream(
    	              new BufferedInputStream(
    	                new FileInputStream(
    	                  new File("files/names"))));
    	      try {
    	    	  names =(String[]) ois.readObject();
    	      } catch (ClassNotFoundException e) {
    	        e.printStackTrace();
    	      }
    	      ois.close();
    	    } catch (FileNotFoundException e) {
    	      e.printStackTrace();
    	    } catch (IOException e) {
    	      e.printStackTrace();
    	    }  
	}
	
	public String getRandomName(){
		return names[(int) (Math.random() * ( names.length ))];
	}
	

	public String getJob(){
		return job;
	}
	
	public String getJobFR(){
		return jobFR;
	}
}
