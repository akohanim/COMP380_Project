package sample;

import java.io.IOException;

public class SignUpUser {
	
	private boolean UserAlreadyExists;
	
	public SignUpUser(String FirstName, String LastName, String Email, String Password) throws IOException {
		UsersData usersData = new UsersData();
		int totalNumberOfUsers = usersData.getLastRow();
		
		for (int i = 0; i < totalNumberOfUsers; i++) {
			
			if(Email.equalsIgnoreCase(usersData.getEmail(i))) {
				UserAlreadyExists = true;
				break;
			}
		}
		
		if (UserAlreadyExists == false) {
			SaveUserAccount saveUserAccount = new SaveUserAccount(FirstName, LastName, Email, Password);
			System.out.println("Sign Up Successful!");
		}
	}
	
	public boolean getUserAlreadyExists() {
		return UserAlreadyExists;
	}
}
