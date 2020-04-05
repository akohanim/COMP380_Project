package sample;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CourseSettings {

	private ExcelFileManager userFile;
	private String CourseName;

	public CourseSettings(String userEmail, String CourseName) {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
		this.CourseName = CourseName;
	}

	public void delete_The_Course() {
		userFile.delete_This_Sheet(CourseName);
	}

	public void rename_The_Course_To(String newName) throws IOException {
		userFile.rename_Sheet(CourseName, newName);
	}

	public void change_The_Course_Section_Number_To(String sectionNumber) throws IOException {
		userFile.update_Cell(sectionNumber, CourseName, 0, 1);
	}

	public void save_The_Course_Icon(String iconDirectory) {
		File icon = new File(iconDirectory);
		File saveAt = new File(CourseName + ".jpeg");
		try {
			FileUtils.copyFile(icon, saveAt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void save_The_Course_Color(int red, int green, int blue) throws IOException {
		userFile.update_Cell(Integer.toString(red), CourseName, 0, 3);
		userFile.update_Cell(Integer.toString(green), CourseName, 0, 4);
		userFile.update_Cell(Integer.toString(blue), CourseName, 0, 5);
	}

}
