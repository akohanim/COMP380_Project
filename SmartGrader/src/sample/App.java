package sample;

import javax.swing.*;

public class App {
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersData userData = new UsersData();
					//LogInPage window = new LogInPage();
					//SaveUserAccount SaveUserAccount = new SaveUserAccount("Sulaiman", "Rahman", "Sulaiman.rahman.591@my.csun.edu", "Pakistan12");
					SignUpUser SignUpUser = new SignUpUser("Sulaiman", "Rahman", "Sulaiman.rahman.591@my.csun.edu", "Pakistan12");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}); 
	}
	
}
