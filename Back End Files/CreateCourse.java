import java.io.IOException;

public class CreateCourse {

	private boolean CourseAlreadyExists;

	public CreateCourse(String userEmail, String CourseName, String SectionNumber) throws IOException {
		ExcelFileManager userFile = new ExcelFileManager(userEmail + ".xlsx");
		
		if (userFile.does_This_Sheet_Exists(CourseName)) {
			CourseAlreadyExists = true;
		} else {			
			userFile.create_A_New_Sheet(CourseName);
			
			userFile.update_Cell("Secttion Number:", CourseName, 0, 0);
			userFile.update_Cell(SectionNumber, CourseName, 0, 1);
			userFile.update_Cell("Course Color: ", CourseName, 0, 2);
			userFile.update_Cell(Integer.toString(generate_Random_Integer_For_RGB()), CourseName, 0, 3);
			userFile.update_Cell(Integer.toString(generate_Random_Integer_For_RGB()), CourseName, 0, 4);
			userFile.update_Cell(Integer.toString(generate_Random_Integer_For_RGB()), CourseName, 0, 5);
			
			userFile.create_A_New_Row(CourseName);//This row(1) is for the names of the weights (Quizzes, Exams, Homework, etc.)
			userFile.update_Cell("Quiz", CourseName, 1, 0);
			userFile.update_Cell("Homework", CourseName, 1, 1);
			userFile.update_Cell("Exam", CourseName, 1, 2);
			userFile.update_Cell("Projects", CourseName, 1, 3);
			
			userFile.create_A_New_Row(CourseName);//This row(2) is for the percentage number of the weights. For example, when it says, quizzes at the top row, below which will be this row, it will say the total percentage of that type. 
			userFile.update_Cell("25", CourseName, 2, 0);
			userFile.update_Cell("25", CourseName, 2, 1);
			userFile.update_Cell("25", CourseName, 2, 2);
			userFile.update_Cell("25", CourseName, 2, 3);
			
			userFile.create_A_New_Row(CourseName);//This row(3) is to save the grade ranges for the graph.
			
			userFile.create_A_New_Row(CourseName);//This row(4) is for the assignment type names.
			/*
			userFile.create_A_New_Row(CourseName);//This row(5) is for the assignment type total percentage. 
			 */
			userFile.create_A_New_Row(CourseName);//This row(5) is for the assignments type, it will say it on the top of each assignment if this is quiz type or exam type or homework type, etc.
			
			userFile.create_A_New_Row(CourseName);//This row(6) is for the assignment names.
			
			userFile.update_Cell("Student DOB", CourseName, 6, 0);
			userFile.update_Cell("Student First Name", CourseName, 6, 1);
			userFile.update_Cell("Student Last Name", CourseName, 6, 2);
			userFile.update_Cell("Student ID", CourseName, 6, 3);
			userFile.update_Cell("Overall Grade", CourseName, 6, 4);
			
			userFile.create_A_New_Row(CourseName);//This row(7) is for the total number of each assignment.
		}
	}
	
	private int generate_Random_Integer_For_RGB() {
		return (int) ((Math.random() * ((255 - 0) + 1)) + 0);
	}
	
	public boolean does_The_Course_Already_Exists() {
		return CourseAlreadyExists;
	}
	
}
