import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Weights {
	private ExcelFileManager userFile;
	
	public Weights(String userEmail) {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
		
	}
	
	private int get_Cell_Of_This_Weight(String courseName, String weightName) throws IOException {
		for (int i = 0; i <= userFile.get_Last_Cell_Of_Row(courseName, 1); i++) {
			if(weightName.equalsIgnoreCase(userFile.get_Data_At(courseName, 1, i))) {
				return i;
			}
		}
		return -1; //This is when the weight does not exists in the list.
	}
	
	private void shift_Columns(String courseName, int rowIndex, int cellIndex) throws IOException, EncryptedDocumentException, InvalidFormatException {
		int lastCellInThisRow = userFile.get_Last_Cell_Of_Row(courseName, rowIndex);
		for (int i = cellIndex; i < lastCellInThisRow - 1; i++) {
			String nextColumnData = userFile.get_Data_At(courseName, rowIndex, i + 1);
			userFile.update_Cell(nextColumnData, courseName, rowIndex, i);
			if (i + 1 == lastCellInThisRow - 1) {
				userFile.delete_This_Cell(courseName, rowIndex, lastCellInThisRow - 1);
			}
 		}
	}
	
	public boolean does_this_Weight_Exists(String courseName, String weightName) throws IOException {
		for (int i = 0; i < userFile.get_Last_Cell_Of_Row(courseName, 1); i++) {
			if (weightName.equalsIgnoreCase(userFile.get_Data_At(courseName, 1, i))) {
				return true;
			}
		}
		return false;
	}
	
	public void add_Weight(String courseName, String weightName, String weightPercentage) throws IOException {
		int lastCellInWeights = userFile.get_Last_Cell_Of_Row(courseName, 1);
		userFile.update_Cell(weightName, courseName, 1, lastCellInWeights);
		userFile.update_Cell(weightPercentage, courseName, 2, lastCellInWeights);
	}
	
	public void remove_Weight(String courseName, String weightName) throws IOException, EncryptedDocumentException, InvalidFormatException {
		int weightCell = get_Cell_Of_This_Weight(courseName, weightName);
		shift_Columns(courseName, 1, weightCell);//removes the name of the weight.
		shift_Columns(courseName, 2, weightCell);//removes the percentage of the weight.
	}
	
	public void edit_Weight(String courseName, String weightName, String newWeightPercentage) throws IOException {
		int weightCell = get_Cell_Of_This_Weight(courseName, weightName);
		userFile.update_Cell(newWeightPercentage, courseName, 2, weightCell);//user should only be able to update the percentage, not the weight.
	}
	
	public int get_Total_Weight_Percentage(String courseName) throws IOException {
		int TotalPercentage = 0;
		for(int i = 0; i < userFile.get_Last_Cell_Of_Row(courseName, 2); i++) {
			TotalPercentage = TotalPercentage + Integer.parseInt(userFile.get_Data_At(courseName, 2, i));
		}
		return TotalPercentage;
	}
	
	public String load_Weight_Percentage(String courseName, String weightName) throws IOException {
		int weightCell = get_Cell_Of_This_Weight(courseName, weightName);
		return userFile.get_Data_At(courseName, 2, weightCell);
	}
	
	public boolean is_Using_Weights_On(String courseName) {
		boolean weightsOnOrOff = false;
		
		try {
			weightsOnOrOff = Boolean.parseBoolean(userFile.get_Data_At(courseName, 2, 4));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weightsOnOrOff;
	}
	
	public void set_Using_Weights_On_Or_Off(String courseName, String OnOrOff) {
		if (OnOrOff.equalsIgnoreCase("On")) {
			
			try {
				userFile.update_Cell("true", courseName, 2, 4);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if (OnOrOff.equalsIgnoreCase("Off")){
			
			try {
				userFile.update_Cell("false", courseName, 2, 4);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
