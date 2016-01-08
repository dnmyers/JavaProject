//Product.java
//created by: Daniel Myers

import java.io.Serializable;

public abstract class Product implements Serializable {
	private static final long serialVersionUID = 8075372467117611630L;
	private String title;
	private double price;
	private Date purchaseDate;
	
	public Product(String t, double p, Date pD){
		setTitle(t);
		setPrice(p);
		setPurchaseDate(pD);
	}
	
	
	public void setTitle(String t){
		title = t;
	}
	public String getTitle(){
		return title;
	}
	
	public void setPrice(double p){
		price = p;
	}
	public double getPrice(){
		return price;
	}
	
	public void setPurchaseDate(Date pD){
		purchaseDate = pD;
	}
	public Date getPurchaseDate(){
		return purchaseDate;
	}
	
	public abstract void setNumberOfSongsPurchased(int nOSP);
	
	public abstract int getNumberOfSongsPurchased();
	
	public String toString(){
		return (getTitle() + " " + getPrice() + " " + getPurchaseDate());
	}	
}
