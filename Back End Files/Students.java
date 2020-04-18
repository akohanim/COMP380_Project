import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Students {
	private ExcelFileManager userFile;
	
	public Students(String userEmail) {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
	}
	
	public boolean does_This_Student_Exists(String courseName, String studentIdNumber) throws IOException {
		int studentRow = get_Student_Row(courseName, studentIdNumber);
		if (studentRow != -1) {
			return true;
		}
		return false;
	}
	
	public void add_Student(String courseName, String firstName, String lastName, String DOB, String studentIdNumber) throws IOException {
		userFile.create_A_New_Row(courseName);
		int lastRow = userFile.get_Last_Row_Of_The_Sheet(courseName);
		userFile.update_Cell(DOB, courseName, lastRow, 0);
		userFile.update_Cell(firstName, courseName, lastRow, 1);
		userFile.update_Cell(lastName, courseName, lastRow, 2);
		userFile.update_Cell(studentIdNumber, courseName, lastRow, 3);
		userFile.update_Cell("0", courseName, lastRow, 4);
	}
	
	public void delete_Student(String courseName, String studentIdNumber) throws IOException {
		int studentRow = get_Student_Row(courseName, studentIdNumber);
		userFile.delete_This_Row(courseName, studentRow);
	}
	
	public void update_This_Student_Information(String courseName, String oldIdNumber, String newFirstName, String newLastName, String newDOB, String newStudentIdNumber) throws IOException {
		int studentRow = get_Student_Row(courseName, oldIdNumber);
		userFile.update_Cell(newDOB, courseName, studentRow, 0);
		userFile.update_Cell(newFirstName, courseName, studentRow, 1);
		userFile.update_Cell(newLastName, courseName, studentRow, 2);
		userFile.update_Cell(newStudentIdNumber, courseName, studentRow, 3);
	}
	
	private int get_Student_Row(String courseName, String studentIdNumber) throws IOException {
		int lastRow = userFile.get_Last_Row_Of_The_Sheet(courseName);
		for (int i = 8; i <= lastRow; i++) {
			if (studentIdNumber.equalsIgnoreCase(userFile.get_Data_At(courseName, i, 3))) {
				return i;
			}
		}
		return -1; //this means the user does not exists
	}
}
