//TitleNotFoundException.java
//Created By: Daniel Myers

import javax.swing.*;
import java.util.*;

public class TitleNotFoundException extends NoSuchElementException{
	private static final long serialVersionUID = 6286121705957403797L;
	
	public TitleNotFoundException(){
		return;
	}

	public TitleNotFoundException(String title){
		JOptionPane.showMessageDialog(null, "Title " + title + " Not Found.", "Error",
			JOptionPane.ERROR_MESSAGE);
	}
}
