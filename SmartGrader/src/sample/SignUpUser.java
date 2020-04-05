package sample;

import java.io.IOException;

public class SignUpUser {

    private boolean UserAlreadyExists;
    private String SheetNameForAccounts = "Accounts";

    public SignUpUser(String FirstName, String LastName, String Email, String Password) throws IOException {
        ExcelFileManager accountsFile = new ExcelFileManager("Accounts.xlsx");

        if (accountsFile.doesFileExists() == false) {
            accountsFile.create_The_File();
            accountsFile.create_A_New_Sheet(SheetNameForAccounts);
            accountsFile.update_Cell("First Name", SheetNameForAccounts, 0, 0);
            accountsFile.update_Cell("Last Name", SheetNameForAccounts, 0, 1);
            accountsFile.update_Cell("Email", SheetNameForAccounts, 0, 2);
            accountsFile.update_Cell("Password", SheetNameForAccounts, 0, 3);
        }

        for (int i = 1; i <= accountsFile.get_Last_Row_Of_The_Sheet(SheetNameForAccounts); i++) {
            if (Email.equalsIgnoreCase(accountsFile.get_Data_At(SheetNameForAccounts, i, 2))) {
                UserAlreadyExists = true;
                break;
            }
        }

        if (UserAlreadyExists == false) {
            accountsFile.create_A_New_Row(SheetNameForAccounts);
            accountsFile.update_Cell(FirstName, SheetNameForAccounts, accountsFile.get_Last_Row_Of_The_Sheet(SheetNameForAccounts), 0); //Saves new user FN
            accountsFile.update_Cell(LastName, SheetNameForAccounts, accountsFile.get_Last_Row_Of_The_Sheet(SheetNameForAccounts), 1); //Saves new user LN
            accountsFile.update_Cell(Email, SheetNameForAccounts, accountsFile.get_Last_Row_Of_The_Sheet(SheetNameForAccounts), 2); //Saves new user Email
            accountsFile.update_Cell(Password, SheetNameForAccounts, accountsFile.get_Last_Row_Of_The_Sheet(SheetNameForAccounts), 3); //Saves new user password
            ExcelFileManager NewUserFile = new ExcelFileManager(Email + ".xlsx");
            NewUserFile.create_The_File(); //This creates a file for the user. this is where all the data for this new user will be saved.
        }
    }

    public boolean does_User_Exists() {
        return UserAlreadyExists;
    }


}
