//Music.java
//Created By: Daniel Myers

import java.io.Serializable;

public class Music extends Product implements Serializable{
	private static final long serialVersionUID = 650558991494730885L;

	public enum GenreType {CLASSICAL, ROCK, COUNTRY};
	private GenreType genre;
	private String artist;
	private int numberOfSongsPurchased;
	
	public Music(String t, double p, Date pD, GenreType g, String a, int nOSP){
		super(t, p, pD);
		setGenre(g);
		setArtist(a);
		setNumberOfSongsPurchased(nOSP);
	}
	
	public void setGenre(GenreType g){
		genre = g;
	}
	public GenreType getGenre(){
		return genre;
	}
	
	public void setArtist(String a){
		artist = a;
	}
	public String getArtist(){
		return artist;
	}
	
	public void setNumberOfSongsPurchased(int nOSP){
		numberOfSongsPurchased = nOSP;
	}
	
	public int getNumberOfSongsPurchased(){
		return numberOfSongsPurchased;
	}
	
	public String toString(){	
		return (super.toString() + " " + getGenre() + " " + getArtist() + " " +
			getNumberOfSongsPurchased());
	}
}
