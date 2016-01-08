//Date.java
//created by: Daniel Myers

import java.io.Serializable;


public class Date implements Serializable{
	private static final long serialVersionUID = -590353754866307587L;
	private int month;
	private int day;
	private int year;
	
	public Date(int m, int d, int y){
		setMonth(m);
		setDay(d);
		setYear(y);
	}

	public void setMonth(int m){
		month = m;
	}
	
	public int getMonth(){
		return month;
	}
	
	public void setDay(int d){
		day = d;
	}
	
	public int getDay(){
		return day;
	}
	
	public void setYear(int y){
		year = y;
	}
	
	public int getYear(){
		return year;
	}

	public String toString(){
		return (getMonth() + "/" + getDay() + "/" + getYear());
	}
}
