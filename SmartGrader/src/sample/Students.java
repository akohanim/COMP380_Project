package sample;

import java.io.IOException;

public class Students {
	private final ExcelFileManager userFile;

	public Students(String userEmail) {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
	}

	public boolean does_This_Student_Exists(String courseName, String studentIdNumber) throws IOException {
		int studentRow = get_Student_Row(courseName, studentIdNumber);
		return studentRow != -1;
	}
	
	public void add_Student(String courseName, String firstName, String lastName, String DOB, String studentIdNumber) throws IOException {
		userFile.create_A_New_Row(courseName);
		int lastRow = userFile.get_Last_Row_Of_The_Sheet(courseName);
		userFile.update_Cell(DOB, courseName, lastRow, 0);
		userFile.update_Cell(firstName, courseName, lastRow, 1);
		userFile.update_Cell(lastName, courseName, lastRow, 2);
		userFile.update_Cell(studentIdNumber, courseName, lastRow, 3);
		for (int currentCell = 4; currentCell < userFile.get_Last_Cell_Of_Row(courseName, 6); currentCell++) {
			userFile.update_Cell("0", courseName, lastRow, currentCell);
		}
		
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

	public String get_Student_DOB(String courseName, int studentRowNumber) {
		String studentDOB = null;
		try {
			studentDOB = userFile.get_Data_At(courseName, studentRowNumber, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentDOB;
	}
}
