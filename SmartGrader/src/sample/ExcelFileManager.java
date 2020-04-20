package sample;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileManager {

    private File file;

    private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;

    private XSSFWorkbook wb;
	private XSSFSheet sh;
	private XSSFCell XSSFcell;
	private XSSFRow XSSFrow;

	public ExcelFileManager(String Directory) {
		try {

            file = new File(Directory);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean doesFileExists() {
		return file.exists();
	}
	
	public void create_The_File() throws IOException {
		file.createNewFile();
	}
	
	public boolean does_This_Sheet_Exists(String sheetName) throws IOException {
		for (int i = 0; i < get_Total_Number_Of_Sheets(); i++) {
			if (sheetName.equalsIgnoreCase(get_Sheet_Name_At(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int get_Total_Number_Of_Sheets() throws IOException {
		try {
			FileInputStream fis = new FileInputStream (file);
			wb = new XSSFWorkbook(fis);
			return wb.getNumberOfSheets();
		} catch (Exception e) {//There will only be an error if there are is not even one sheet
			return 0;
		}
	}
	
	public int get_Last_Row_Of_The_Sheet(String sheetName) throws IOException {
		if (does_This_Sheet_Exists(sheetName) == true) {
			try {
				FileInputStream fis = new FileInputStream (file);
				workbook = WorkbookFactory.create(fis);
				sheet = workbook.getSheet(sheetName);
				return sheet.getLastRowNum();
			} catch (Exception e) {
				return -1;
			}
		} else {
			System.out.println("Error at get_Last_Row_Of_The_Sheet(). " + sheetName + " does not exists.");
			return -1; //Sheet Does not exists
		}
	}
	
	public int get_Last_Cell_Of_Row(String sheetName, int rowIndex) throws IOException {
		if (does_This_Sheet_Exists(sheetName) == true) {
			try {
				FileInputStream fis = new FileInputStream (file);
				workbook = WorkbookFactory.create(fis);
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(rowIndex);
				return row.getLastCellNum();
			} catch (Exception e) {
				System.out.println("Error at get_Last_Cell_Of_Row(). " + rowIndex + " is an invalid row number.");
				return -1;//Returns -1 when there's invalid Row Number
			}
		} else {
			System.out.println("Error at get_Last_Cell_Of_Row(). " + sheetName + " does not exists.");
			return -1;//Sheet does not exists
		}
	}
	
	public String get_Sheet_Name_At(int sheetIndex) throws IOException {
		if (sheetIndex >= 0 && sheetIndex < get_Total_Number_Of_Sheets()) { //Sheet number must not be a negative number and must not exceed the total number of sheets
			FileInputStream fis = new FileInputStream (file);
			wb = new XSSFWorkbook(fis);
			return wb.getSheetName(sheetIndex);
		} else {
			return "Invalid Sheet Number";
		}
	}
	
	public int get_Sheet_Index_For(String sheetName) throws IOException {
		
		for (int i = 0; i <= get_Total_Number_Of_Sheets(); i++) {
			if (get_Sheet_Name_At(i).equals(sheetName)) {
				return i;
			}
		}
		return -1; //If sheetName doesn't exists then it will return -1;
	}
	
	public void create_A_New_Sheet(String sheetName) throws IOException {
		try {
			if (get_Total_Number_Of_Sheets() == 0) { //this over writes everything, therefore we want to run this only when there's no data in the file at all, because otherwise it will delete everything.
                FileOutputStream fos = new FileOutputStream(file);
                wb = new XSSFWorkbook();
                sh = wb.createSheet(sheetName);
                XSSFrow = sh.createRow(0);
                XSSFcell = XSSFrow.createCell(0);
                XSSFcell.setCellValue(" ");
                wb.write(fos);
                fos.close();
            } else { //this won't run if the file is completely empty, meaning when there is not not even one sheet in the file. So, that's why we have the above code that will create the first sheet.
                FileInputStream fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);
                sheet = workbook.createSheet(sheetName);
                row = sheet.createRow(0);
                cell = row.createCell(0);
                cell.setCellValue(" ");
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                workbook.close();
                fos.close();
                fis.close();
            }
		} catch (Exception e) {
			System.out.println("Error at: create_A_New_Sheet(). " + sheetName + " Already Exists.");
		}
	}
	
	public void rename_Sheet(String sheetCurrentName, String sheetNewName) throws IOException {
		if (does_This_Sheet_Exists(sheetNewName) == true) {
			System.out.println("Sheet with the name " + sheetNewName + " already exists");
		} else if (does_This_Sheet_Exists(sheetCurrentName) == false) {
			System.out.println("Invalid Sheet Name. " + sheetCurrentName + " was Not found.");
		} else {
            FileInputStream fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
            int SheetIndex = wb.getSheetIndex(sheetCurrentName);
            wb.setSheetName(SheetIndex, sheetNewName);
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            wb.close();
            fis.close();
            fos.close();
            System.out.println(sheetCurrentName + " was sucessfully renamed to " + sheetNewName + ".");
        }
		
	}
	
	public void rename_The_File(String oldName, String newName) {
		File oldFile = new File(oldName + ".xlsx");
		File newFile = new File(newName + ".xlsx");
		oldFile.renameTo(newFile);
	}
	
	public String get_Data_At(String sheetName, int rowIndex, int cellIndex) throws IOException {
		
		if (does_This_Sheet_Exists(sheetName) == true) {
			int lastRowInTheSheet = get_Last_Row_Of_The_Sheet(sheetName);
			if (lastRowInTheSheet >= 0 && rowIndex >= 0 && rowIndex <= lastRowInTheSheet) {
				FileInputStream fis = new FileInputStream (file);
				wb = new XSSFWorkbook(fis);
				sh = wb.getSheet(sheetName);
				XSSFrow = sh.getRow(rowIndex);
				
				XSSFcell = XSSFrow.getCell(cellIndex);
				
				if (XSSFcell == null || XSSFcell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
					return "";
				} else {
					return XSSFcell.getStringCellValue();
				}
			} else {
				return "Row " + rowIndex + " is not created.";
			}
			
		} else {
			System.out.println(sheetName + " does not exists.");
			return sheetName + " does not exists.";	
		}
	}
	
	public void create_A_New_Row(String sheetName) throws IOException {
		int newRowNumber = get_Last_Row_Of_The_Sheet(sheetName) + 1;
		try {
            FileInputStream fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
            sh = wb.getSheet(sheetName);
            XSSFrow = sh.createRow(newRowNumber);
            XSSFcell = XSSFrow.createCell(0);
            XSSFcell.setCellValue("");
            fis.close();
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            wb.close();
            fos.close();
        } catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update_Cell(String data, String sheetName, int rowIndex, int cellIndex) throws IOException {
		if (does_This_Sheet_Exists(sheetName) == true) {
			if (rowIndex >= 0 && rowIndex <= get_Last_Row_Of_The_Sheet(sheetName)) {
				if (cellIndex >= 0) {
					try {
						
						FileInputStream fis = new FileInputStream (file);
						workbook = WorkbookFactory.create(fis);
						sheet = workbook.getSheet(sheetName);
						row = sheet.getRow(rowIndex);
						cell = row.createCell(cellIndex);
						
						cell.setCellValue(data);
						
						FileOutputStream fos = new FileOutputStream (file);
						workbook.write(fos);
						workbook.close();
						fos.close();
						fis.close();
						
					} catch (Exception e) {
						System.out.println("Error at updating Cell");
						e.printStackTrace();
					}
					
				} else {
					System.out.println("Invalid Cell Number");
				}
			}
			else {
				System.out.println("Invalid Row Number");
			}
		} else {
			System.out.println(sheetName + " does not Exists");
		}
	}
	
	public void delete_This_Row(String sheetName, int rowIndex) throws IOException {
		int lastRowNumber = get_Last_Row_Of_The_Sheet(sheetName);
		try {
			
			if (rowIndex >= 0 && rowIndex < lastRowNumber) {
				
				FileInputStream fis = new FileInputStream (file);
				workbook = WorkbookFactory.create(fis);
				sheet = workbook.getSheet(sheetName);
				
				sheet.shiftRows(rowIndex + 1, lastRowNumber, -1);
				
				FileOutputStream fos = new FileOutputStream (file);
				workbook.write(fos);
				workbook.close();
				fos.close();
				fis.close();
				
			} else if (rowIndex == lastRowNumber) {
				
				FileInputStream fis = new FileInputStream (file);
				workbook = WorkbookFactory.create(fis);
				sheet = workbook.getSheet(sheetName);
				
				sheet.removeRow(sheet.getRow(rowIndex));
				
				FileOutputStream fos = new FileOutputStream (file);
				workbook.write(fos);
				workbook.close();
                fos.close();
                fis.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete_This_Cell(String sheetName, int rowIndex, int cellIndex) throws IOException, EncryptedDocumentException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(file);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowIndex);
        row.removeCell(row.getCell(cellIndex));

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();
        fos.close();
        fis.close();
    }

    public void delete_This_Sheet(String sheetName) {

        try {
            if (does_This_Sheet_Exists(sheetName) == true) {
                FileInputStream fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);

                workbook.removeSheetAt(get_Sheet_Index_For(sheetName));

                FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
				workbook.close();
				fos.close();
				fis.close();
			} else {
				System.out.println(sheetName + " does not exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
 