import java.io.IOException;

public class CurvedResults {
	private ExcelFileManager userFile;
	private double curvedValue;
	
	public CurvedResults(String userEmail, double curvedValue) {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
		this.curvedValue = curvedValue;
	}
	
	public String get_Student_First_Name(String courseName, int studentRowIndex) throws IOException {
		return userFile.get_Data_At(courseName, studentRowIndex, 1);
	}
	
	public String get_Student_Last_Name(String courseName, int studentRowIndex) throws IOException {
		return userFile.get_Data_At(courseName, studentRowIndex, 2);
	}
	
	public String get_Student_Id_Number(String courseName, int studentRowIndex) throws IOException {
		return userFile.get_Data_At(courseName, studentRowIndex, 3);
	}

	public String get_Student_Overall_Grade(String courseName, int studentRowIndex) throws IOException {
		return userFile.get_Data_At(courseName, studentRowIndex, 4);
	}
	
	public double get_Student_Curved_Grade(String courseName, int studentRowIndex) throws IOException {
		String studentOverallGrade = get_Student_Overall_Grade(courseName, studentRowIndex);
		return Double.parseDouble(studentOverallGrade) + curvedValue;
	}
	
	public int get_Last_Row(String courseName) throws IOException {
		return userFile.get_Last_Row_Of_The_Sheet(courseName);
	}
}
