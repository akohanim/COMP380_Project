package sample;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Assignments {
    private ExcelFileManager userFile;

    public Assignments(String userEmail) {
        userFile = new ExcelFileManager(userEmail + ".xlsx");
    }

    private void shift_Columns(String courseName, int rowIndex, int cellIndex) throws IOException, EncryptedDocumentException, InvalidFormatException {
        int lastCellInThisRow = userFile.get_Last_Cell_Of_Row(courseName, rowIndex);
        for (int i = cellIndex; i <= lastCellInThisRow - 1; i++) {
            System.out.println("Last cell in the row is: " + lastCellInThisRow);
            String nextColumnData = userFile.get_Data_At(courseName, rowIndex, i + 1);
            userFile.update_Cell(nextColumnData, courseName, rowIndex, i);
            if (i + 1 == lastCellInThisRow - 1) {
                userFile.delete_This_Cell(courseName, rowIndex, lastCellInThisRow - 1);
            }
        }
    }

    public boolean does_This_Assignment_Exists(String courseName, String assignmentName) throws IOException {
        int lastCellInTheAssignment = userFile.get_Last_Cell_Of_Row(courseName, 6);
        for (int i = 5; i < lastCellInTheAssignment; i++) {
            if (userFile.get_Data_At(courseName, 6, i).equalsIgnoreCase(assignmentName)) {
                return true;
            }
        }
        return false;
    }

    public String load_Assignment_Name(String courseName, int cellIndex) throws IOException {
        return userFile.get_Data_At(courseName, 6, cellIndex);
    }

    public String load_Assignment_Type(String courseName, int cellIndex) throws IOException {
        return userFile.get_Data_At(courseName, 5, cellIndex);
    }

    public int load_Assignment_Points(String courseName, int cellIndex) throws NumberFormatException, IOException {
        return Integer.parseInt(userFile.get_Data_At(courseName, 7, cellIndex));
    }

    public void add_Assignment(String courseName, String assignmentName, String points, String type) throws IOException {
        int lastCellInTheRowOfAssignments = userFile.get_Last_Cell_Of_Row(courseName, 6);
        userFile.update_Cell(type, courseName, 5, lastCellInTheRowOfAssignments);
        userFile.update_Cell(assignmentName, courseName, 6, lastCellInTheRowOfAssignments);
        userFile.update_Cell(points, courseName, 7, lastCellInTheRowOfAssignments);

        for(int currentRow = 8; currentRow < userFile.get_Last_Row_Of_The_Sheet(courseName); currentRow++) {
            userFile.update_Cell("0", courseName, currentRow, lastCellInTheRowOfAssignments);
        }
    }

    public void delete_Assignment(String courseName, int cellNumber) throws IOException, EncryptedDocumentException, InvalidFormatException {
        int lastRowInTheFile = userFile.get_Last_Row_Of_The_Sheet(courseName);
        for (int i = 5; i <= lastRowInTheFile; i++) {//Row 5 is where the Assignment type starts.
            System.out.println("current row is: " + i);
            shift_Columns(courseName, i, cellNumber);
        }
    }

    public void edit_Assignment(String courseName, int cellIndex, String assignmentName, String points, String type) throws IOException {
        userFile.update_Cell(type, courseName, 5, cellIndex);
        userFile.update_Cell(assignmentName, courseName, 6, cellIndex);
        userFile.update_Cell(points, courseName, 7, cellIndex);
    }

}