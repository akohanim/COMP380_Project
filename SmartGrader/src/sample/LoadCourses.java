package sample;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoadCourses {

	private ExcelFileManager userFile;

	public LoadCourses(String userEmail) throws IOException {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
	}

	public int get_Total_Number_Of_Courses() throws IOException {//return 0 if there are no sheets and return 1 for the first sheet, but remember the "index" for the first sheet is 0;
		return userFile.get_Total_Number_Of_Sheets();
	}

	public String get_Course_Name_For_Index(int sheetIndex) throws IOException {
		return userFile.get_Sheet_Name_At(sheetIndex);
	}

	public String get_Course_Section_Number_For_Index(int sheetIndex) throws IOException {
		return userFile.get_Data_At(userFile.get_Sheet_Name_At(sheetIndex), 0, 1);
	}
	
	public String get_Course_Icon(String courseName) {
		String fileName = courseName + ".jpeg";
		File courseIcon = new File(fileName);
		if (courseIcon.exists() == true) {
			return courseName + ".jpeg";
		} else {
			return "";
		}
	}
	
	public Color get_Course_Color(String courseName) throws NumberFormatException, IOException {
		int red = Integer.parseInt(userFile.get_Data_At(courseName, 0, 3));
		int green = Integer.parseInt(userFile.get_Data_At(courseName, 0, 4));
		int blue = Integer.parseInt(userFile.get_Data_At(courseName, 0, 5));
		return new Color(red, green, blue);
	}

}
