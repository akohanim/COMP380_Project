package sample;

import java.io.IOException;

public class Print {
    private final ExcelFileManager userFile;
    private final ExcelFileManager printFile = new ExcelFileManager("printing data.xlsx");
    private final String printingDataSheet = "Printing Data";
    private String[][] printDataIn2DArray;

    public Print(String userEmail) {
        userFile = new ExcelFileManager(userEmail + ".xlsx");

    }

    private void clean_Or_Create_The_Print_File_If_It_Does_Not_Exists() {
        if (printFile.doesFileExists() == false) {
            try {
				printFile.create_The_File();
				printFile.create_A_New_Sheet(printingDataSheet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {//Removing the old data
				if (printFile.does_This_Sheet_Exists(printingDataSheet) == true) {
					printFile.delete_This_Sheet(printingDataSheet);
					printFile.create_A_New_Sheet(printingDataSheet);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void load_Data_To_The_Print_File(String data, int row, int column) {
		//int row and int column refers to the row and column of the print file.
		try {
			printFile.update_Cell(data, printingDataSheet, row, column);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void name_Is_Selected(String courseName) {
		clean_Or_Create_The_Print_File_If_It_Does_Not_Exists();
		
		load_Data_To_The_Print_File("First Name", 0, 0);
		load_Data_To_The_Print_File("Last Name", 0, 1);
		load_Data_To_The_Print_File("Overall Grade", 0, 2);
		
		try {
			
			for (int i = 8, j = 1; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++, j++) {
				printFile.create_A_New_Row(printingDataSheet);

				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 1), j, 0);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 2), j, 1);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 4), j, 2);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void id_Number_Is_Selected(String courseName) {
		clean_Or_Create_The_Print_File_If_It_Does_Not_Exists();
		
		load_Data_To_The_Print_File("ID Number", 0, 0);
		load_Data_To_The_Print_File("Overall Grade", 0, 1);
		
		try {
			for (int i = 8, j = 1; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++, j++) {
				printFile.create_A_New_Row(printingDataSheet);

				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 3), j, 0);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 4), j, 1);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void DOB_Is_Selected(String courseName) {
		clean_Or_Create_The_Print_File_If_It_Does_Not_Exists();
		
		load_Data_To_The_Print_File("Date of Birth", 0, 0);
		load_Data_To_The_Print_File("Overall Grade", 0, 1);
		
		try {
			for (int i = 8, j = 1; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++, j++) {
				printFile.create_A_New_Row(printingDataSheet);

				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 0), j, 0);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 4), j, 1);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void all_Is_Selected(String courseName) {
		clean_Or_Create_The_Print_File_If_It_Does_Not_Exists();
		

		load_Data_To_The_Print_File("First Name", 0, 0);
		load_Data_To_The_Print_File("Last Name", 0, 1);
		load_Data_To_The_Print_File("Date of Birth", 0, 2);
		load_Data_To_The_Print_File("ID Number", 0, 3);
		load_Data_To_The_Print_File("Overall Grade", 0, 4);
		
		try {
			for (int i = 8, j = 1; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++, j++) {
				printFile.create_A_New_Row(printingDataSheet);

				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 1), j, 0);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 2), j, 1);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 0), j, 2);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 3), j, 3);
				load_Data_To_The_Print_File(userFile.get_Data_At(courseName, i, 4), j, 4);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String[][] get_Printing_Data_In_A_2D_Array (){
		int numberOfRows = 0;
		int numberOfColumns = 0;
		try {
			numberOfRows = printFile.get_Last_Row_Of_The_Sheet(printingDataSheet);
			numberOfColumns = printFile.get_Last_Cell_Of_Row(printingDataSheet, numberOfRows);
		} catch (IOException e) {
			e.printStackTrace();
		}
		printDataIn2DArray = new String[numberOfRows + 1][numberOfColumns + 1];
		for (int i = 0; i <= numberOfRows; i++) {

			for (int j = 0; j <= numberOfColumns; j++) {
				try {
					printDataIn2DArray[i][j] = printFile.get_Data_At(printingDataSheet, i, j);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return printDataIn2DArray;
	}
	
}
