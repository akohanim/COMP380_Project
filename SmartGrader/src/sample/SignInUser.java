package sample;

import java.io.IOException;

public class SignInUser {
	
	private boolean UserExists;
	private boolean UserPasswordMatches;
	
	public SignInUser(String Email, String Password) throws IOException {
		UsersData usersData = new UsersData();
		int totalNumberOfUsers = usersData.getLastRow();
		for (int i = 0; i < totalNumberOfUsers; i++) {
			if (Email.equalsIgnoreCase(usersData.getEmail(i))) {
				UserExists = true;
				if (Password.equals(usersData.getPassword(i))) {
					UserPasswordMatches = true;
					System.out.println("Log In Successful!");
				}
				break;
			} else {
				UserExists = false;
				UserPasswordMatches = false;
			}
		}
	}
	
	public boolean DoesUserExists() {
		return UserExists;
	}
	public boolean DoesUserPasswordMatches() {
		return UserPasswordMatches;
	}
	
}
