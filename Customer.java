//Customer.java
//created by :Daniel Myers

import java.util.*;
import java.io.*;

public class Customer implements PurchaseHistory, Serializable{
	private static final long serialVersionUID = 5944615789089180687L;
	private String name;
	private Address address;
	private int accountNumber;
	private ArrayList<Product> productList = new ArrayList<Product>();
	private int music;
	private int app;
	
	public Customer(String n, Address a, int aN){
		setName(n);
		setAddress(a);
		setAccountNumber(aN);
	}
	
	public void setName(String n){
		name = n;
	}
	public String getName(){
		return name;
	}
	
	public void setAddress(Address a){
		address = a;
	}
	public Address getAddress(){
		return address;
	}
	
	public void setAccountNumber(int aN){
		accountNumber = aN;
	}
	public int getAccountNumber(){
		return accountNumber;
	}
	
	public void addToProductList(Product p){
		productList.add(p);
	}
	
	public ArrayList<Product> getProductList(){
		return this.productList;
	}
	
	public double calculateCharge(){
		double charge = 0.0;
		
		for(Product p: productList){
			if(p.getClass().getName().equals("Music")){
				charge += ((Music)p).getNumberOfSongsPurchased() * p.getPrice();
				music++;
			}
			else {
				charge += p.getPrice();
				app++;
			}			
		}
		return charge;
	}
	
	public String createHistory(){
		String output = "";
		output = String.format("%d Music products %d Apps $%.2f", music, app, calculateCharge());
		return output;
	}
	
	public String toString(){
		return (name + " " + createHistory());
	}
}
