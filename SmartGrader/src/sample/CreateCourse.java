package sample;

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
        }
    }

    private int generate_Random_Integer_For_RGB() {
        return (int) ((Math.random() * ((255 - 0) + 1)) + 0);
    }

    public boolean does_The_Course_Already_Exists() {
        return CourseAlreadyExists;
    }

}
