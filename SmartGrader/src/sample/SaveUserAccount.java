package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SaveUserAccount {
	
	private XSSFWorkbook wb;
	private XSSFSheet sh;
	private XSSFCell x;
	private XSSFRow y;
	FileInputStream fis;
	FileOutputStream fos;
	int lastRow;
	
	public SaveUserAccount(String FirstName, String LastName, String Email, String Password) throws IOException {
		UsersData userData = new UsersData();
		lastRow = userData.getLastRow();
		fis = new FileInputStream("Accounts.xlsx");
		wb = new XSSFWorkbook(fis);
		sh = wb.getSheet("Accounts");
		y = sh.createRow(lastRow);
		Save_To_Account_File_At(0, FirstName);
		Save_To_Account_File_At(1, LastName);
		Save_To_Account_File_At(2, Email);
		Save_To_Account_File_At(3, Password);
		fos = new FileOutputStream("Accounts.xlsx");
		wb.write(fos);
		fos.close();
	}
	
	public void Save_To_Account_File_At(int Column, String text) throws IOException {
		x = y.createCell(Column);
		x.setCellValue(text);
	}
}
