package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UsersData {
	
	private String LogInData[][];
	private File Accounts;
	private XSSFWorkbook wb;
	private XSSFSheet sh;
	private XSSFCell x;
	private XSSFRow y;
	private int lastRow = 0;
	private int lastColumn = 0;	
	
	public UsersData () throws IOException {
		
		Create_Account_File_If_It_Does_Not_Exist();
		Set_Size_To_Log_In_Data_Array();
		Load_Data_To_Log_In_Data_Array();
		
	}
	
	public void Create_Account_File_If_It_Does_Not_Exist() throws IOException {
		try {
			Accounts = new File("Accounts.xlsx");
			boolean DoesAccountsFileExists = Accounts.exists();
			if (DoesAccountsFileExists == false) {
				wb = new XSSFWorkbook();
				FileOutputStream fos = new FileOutputStream(new File("Accounts.xlsx"));
				sh = wb.createSheet("Accounts");
				wb.write(fos);
				fos.close();
				System.out.println("New File was created");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void Set_Size_To_Log_In_Data_Array() throws IOException {
		FileInputStream fis = new FileInputStream(Accounts);
		wb = new XSSFWorkbook(fis);
		sh = wb.getSheet("Accounts");
		lastRow = sh.getLastRowNum();
		
		y = sh.getRow(lastRow);
		try {
			lastColumn = y.getLastCellNum();
		} catch (Exception e) {
			System.out.println("There isn't any account that's been created yet");
		}
		
		if (lastColumn != 0) { 
			lastRow++;
		}
		
		LogInData = new String[lastRow][lastColumn];
		fis.close();
	}
	
	public void Load_Data_To_Log_In_Data_Array () {
		for (int i = 0; i < lastRow; i++) {
			for (int j = 0; j < lastColumn; j++) {
				LogInData[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
	}
	
	public int getLastRow () {
		return lastRow;
	}
	
	public int getLastColumn() {
		return lastColumn;
	}
	
	public String getFirstName(int row) {
		return LogInData[row][0];
	}
	
	public String getLastName(int row) {
		return LogInData[row][1];
	}
	
	public String getEmail(int row) {
		return LogInData[row][2];
	}
	
	public String getPassword(int row) {
		return LogInData[row][3];
	}
}
