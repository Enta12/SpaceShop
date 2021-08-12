package fr.spaceShop.modele.globalInformations;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Date implements Serializable{
	public static Date currentDate = null;
	private int day;
	private int month;
	private int year;
	
	public static void init(int year){
		currentDate = new Date(year);
	}
	
	public static void init(int day, int mounth, int year){
		currentDate = new Date(day, mounth, year);
	}
	
	public Date(){
		day = 12;
		month = 12;
		year = 9812;
	}
	
	public Date(int year){
		day = 12;
		month = 12;
		this.year = year;
	}
	
	public Date(Date date){
		day = date.getDay();
		month = date.getMonth();
		year = date.getYear();
	}
	
	public Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int getDay(){
		return day;
	}
	
	public int getMonth(){
		return month;
	}
	
	public int getYear(){
		return year;
	}
	
	public boolean isBefore(Date date2){
		if(year < date2.getYear()) return true;
		else if (year > date2.getYear()) return false;
		else {
			if(month < date2.getMonth()) return true;
			else if (month > date2.getMonth()) return false;
			else {
				if(day < date2.getDay()) return true;
				else return false;
			}
		}
	}
	
	public boolean isSame(Date date2){
		if(year == date2.getYear() && month == date2.getMonth() && day == date2.getDay()) return true;
		else return false;
	}
	
	
	
	
	public static boolean nextDay(Date date){
		boolean leapYear = false;
		if(date.year%4 == 0 || (date.year%100 == 0 && date.year%400 != 0)){
			leapYear = true;
		}
		if(date.day < 25){
			date.day += 1;
			return false;
		}
		else if(date.month==2 &&((leapYear  && date.day == 25)||(!leapYear  && date.day == 27))){
			date.day = 1;
			date.month =3;
			return true;
		}
		else if(date.day == 30 && (date.month == 2 || date.month == 4 || date.month == 6 || date.month == 8 ||date.month == 10)){
			date.day +=1;
			return false;
		}
		else if(date.day < 31) {
			date.day += 1;
			return false;
		}
		else if(date.day == 31 && date.month == 12){
			date.day =1;
			date.month = 1;
			date.year += 1;
			return true;
		}
		
		else{
			date.day = 1;
			date.month += 1;
			return true;
		}
	}
	
	public String toString(){
		return day + "/" + month + "/" + year;
	}

	public static void load() {
		ObjectInputStream ois;
    	try {
    		ois = new ObjectInputStream(
    	              new BufferedInputStream(
    	                new FileInputStream(
    	                  new File("files/gameDate"))));
    	            

    	      try {
    	    	  currentDate =(Date)ois.readObject();
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
	
	
}
