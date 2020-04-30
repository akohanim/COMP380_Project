package sample;
import java.io.IOException;

public class CreateCourse {

	private ExcelFileManager userFile;
	private boolean CourseAlreadyExists;

	public CreateCourse(String userEmail, String courseName, String sectionNumber) throws IOException {
		userFile = new ExcelFileManager(userEmail + ".xlsx");

		if (userFile.does_This_Sheet_Exists(courseName)) {
			CourseAlreadyExists = true;
		} else {
			userFile.create_A_New_Sheet(courseName);

			userFile.update_Cell("Secttion Number:", courseName, 0, 0);
			userFile.update_Cell(sectionNumber, courseName, 0, 1);
			userFile.update_Cell("Course Color: ", courseName, 0, 2);
			userFile.update_Cell(Integer.toString(generate_Random_Integer_For_RGB()), courseName, 0, 3);
			userFile.update_Cell(Integer.toString(generate_Random_Integer_For_RGB()), courseName, 0, 4);
			userFile.update_Cell(Integer.toString(generate_Random_Integer_For_RGB()), courseName, 0, 5);

			userFile.create_A_New_Row(courseName);//This row(1) is for the names of the weights (Quizzes, Exams, Homework, etc.)
			userFile.create_A_New_Row(courseName);//This row(2) is for the percentage number of the weights. For example, when it says, quizzes at the top row, below which will be this row, it will say the total percentage of that type. 
			set_Default_Weights_Their_Names(courseName);

			userFile.create_A_New_Row(courseName);//This row(3) is for the letters of the grade ranges.
			userFile.create_A_New_Row(courseName);//This row(4) is to save the grade ranges for the graph.
			set_Default_Grade_Letters_And_Their_Ranges(courseName);

			userFile.create_A_New_Row(courseName);//This row(5) is for the assignments type, it will say it on the top of each assignment if this is quiz type or exam type or homework type, etc.

			userFile.create_A_New_Row(courseName);//This row(6) is for the assignment names.

			userFile.update_Cell("Student DOB", courseName, 6, 0);
			userFile.update_Cell("First Name", courseName, 6, 1);
			userFile.update_Cell("Last Name", courseName, 6, 2);
			userFile.update_Cell("Student ID", courseName, 6, 3);
			userFile.update_Cell("Overall Grade", courseName, 6, 4);

			userFile.create_A_New_Row(courseName);//This row(7) is for the total number of each assignment.
		}
	}

	private int generate_Random_Integer_For_RGB() {
		return (int) ((Math.random() * ((255 - 0) + 1)) + 0);
	}

	private void set_Default_Weights_Their_Names(String courseName) {
		try {
			userFile.update_Cell("Quiz", courseName, 1, 0);
			userFile.update_Cell("Homework", courseName, 1, 1);
			userFile.update_Cell("Exam", courseName, 1, 2);
			userFile.update_Cell("Projects", courseName, 1, 3);

			userFile.update_Cell("25", courseName, 2, 0);
			userFile.update_Cell("25", courseName, 2, 1);
			userFile.update_Cell("25", courseName, 2, 2);
			userFile.update_Cell("25", courseName, 2, 3);

			userFile.update_Cell("false", courseName, 2, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void set_Default_Grade_Letters_And_Their_Ranges(String courseName) {
		try {
			userFile.update_Cell("A", courseName, 3, 0);
			userFile.update_Cell("B", courseName, 3, 1);
			userFile.update_Cell("C", courseName, 3, 2);
			userFile.update_Cell("D", courseName, 3, 3);
			userFile.update_Cell("F", courseName, 3, 4);

			userFile.update_Cell("90", courseName, 4, 0);
			userFile.update_Cell("80", courseName, 4, 1);
			userFile.update_Cell("70", courseName, 4, 2);
			userFile.update_Cell("60", courseName, 4, 3);
			userFile.update_Cell("0", courseName, 4, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean does_The_Course_Already_Exists() {
		return CourseAlreadyExists;
	}

}