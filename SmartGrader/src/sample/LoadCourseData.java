package sample;
import java.io.IOException;

public class LoadCourseData {

	private ExcelFileManager userFile;
	private String[][] ArrayWithAllTheCourseData;
	public LoadCourseData(String userEmail) {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
	}

	public String[][] get_2D_Array_Loaded_With_The_Course_Data(String courseName) {
		int numberOfRows = 0;
		int numberOfColumns = 0;

		try {//Getting proper numberOfRows and Columns for the ArrayWithAllTheCourseData, so that all of the data will fit in there
			numberOfRows = userFile.get_Last_Row_Of_The_Sheet(courseName) - 6;
			numberOfColumns = userFile.get_Last_Cell_Of_Row(courseName, 6) - 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayWithAllTheCourseData = new String[numberOfRows][numberOfColumns]; //assigned length to the array

		for(int col = 0; col < numberOfColumns; col++) {//putting all the assignments and their total points in the array. Assignments in the spreadsheet are at row 6 and total points of the assignments are at row 7.
			try {
				if (col < 4) {
					ArrayWithAllTheCourseData[0][col] = userFile.get_Data_At(courseName, 6, col + 1);//we call col + 1, because we are skipping the first column of the spreadsheet, and we can't start col with 0 because we need it for the array.
				} else {//This is where the assignments starts. On the spreadsheets the assignments starts at column 5
					ArrayWithAllTheCourseData[0][col] = userFile.get_Data_At(courseName, 6, col + 1) + " (" + userFile.get_Data_At(courseName, 7, col + 1) + ")";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(int row = 1, rowOfTheSheet = 8; row < numberOfRows; row++, rowOfTheSheet++) {//Load the students and their data to the array.
			for (int column = 0; column < numberOfColumns; column++) {
				try {
					ArrayWithAllTheCourseData[row][column] = userFile.get_Data_At(courseName, rowOfTheSheet, column + 1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return ArrayWithAllTheCourseData;
	}
}