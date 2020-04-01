import java.io.IOException;

public class SignInUser {
	
	private boolean UserExists = false;
	private boolean UserPasswordMatches = false;
	
	
	public SignInUser(String Email, String Password) throws IOException {
		
		ExcelFileManager accountsFile = new ExcelFileManager("Accounts.xlsx");
		
		for (int i = 1; i <= accountsFile.get_Last_Row_Of_The_Sheet("Accounts"); i++) {
			if (Email.equalsIgnoreCase(accountsFile.get_Data_At("Accounts", i, 2))) {
				UserExists = true;
				if (Password.equals(accountsFile.get_Data_At("Accounts", i, 3))){
					UserPasswordMatches = true;
				}
				break;
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
