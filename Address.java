//Address.java
//created by: Daniel Myers

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = -706823771477674868L;
		private String street;
		private String city;
		private String state;
		private int zip;
		
		public Address(String str, String c, String sta, int z){
			setStreet(str);
			setCity(c);
			setState(sta);
			setZip(z);
		}
		
		public void setStreet(String str){
			street = str;
		}
		
		public String getStreet(){
			return street;
		}
		
		public void setCity(String c){
			city = c;
		}
		
		public String getCity(){
			return city;
		}
		
		public void setState(String sta){
			state = sta;
		}
		
		public String getState(){
			return state;
		}
		
		public void setZip(int z){
			zip = z;
		}
		public int getZip(){
			return zip;
		}
		
		public String toString(){
			return (getStreet() + "\n" + getCity() + ", " + getState() + " " + getZip());
		}
}
