package sample;

import java.io.IOException;

public class SaveAccountSettings {

    public SaveAccountSettings(String OldEmail, String FirstName, String LastName, String NewEmail, String Password) throws IOException {
        ExcelFileManager userFile = new ExcelFileManager(OldEmail + ".xlsx");
        ExcelFileManager accountsFile = new ExcelFileManager("Accounts.xlsx");

        userFile.rename_The_File(OldEmail, NewEmail);

        int userAccountRow = 0;

        for (int i = 1; i <= accountsFile.get_Last_Row_Of_The_Sheet("Accounts"); i++) {
            if (accountsFile.get_Data_At("Accounts", i, 2).equalsIgnoreCase(OldEmail)) {
                userAccountRow = i;
            }
        }

        accountsFile.update_Cell(FirstName, "Accounts", userAccountRow, 0);
        accountsFile.update_Cell(LastName, "Accounts", userAccountRow, 1);
        accountsFile.update_Cell(NewEmail, "Accounts", userAccountRow, 2);
        accountsFile.update_Cell(Password, "Accounts", userAccountRow, 3);

    }
}
