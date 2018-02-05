package Test.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	// public static void main(String[] args)
	// {
	// readFile();
	// System.out.println(readData.size());
	// }

	static ArrayList<String> readData = new ArrayList<String>();

	public static void readFile() {

		try {
			FileInputStream excelToRead = new FileInputStream("C:\\Users\\Admin\\Desktop\\thedemosite.xlsx");
			Workbook wb = new XSSFWorkbook(excelToRead);

			Sheet Sheet = wb.getSheetAt(0);
			Row row;
			Cell cell;

			Iterator<Row> rows = Sheet.rowIterator();

			while (rows.hasNext()) {
				row = rows.next();
				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					cell = cells.next();
					//System.out.print(cell.getStringCellValue() + " ");
					readData.add(cell.getStringCellValue());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Sheet sheet;

	public static Workbook workBook;

	public static Cell cell;

	public static Row row;

	public static Object[][] readIntoArray(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			workBook = new XSSFWorkbook(ExcelFile);

			sheet = workBook.getSheet(SheetName);


			int totalRows = sheet.getLastRowNum()+1;
			
			System.out.println("rows " + totalRows);

			int totalCols = 3;

			tabArray = new String[totalRows][totalCols];

			// iterating through excel sheet
			for (int i = 0; i < totalRows; i++) {

				for (int j = 0; j < totalCols; j++) {

					tabArray[i][j] = getCellData(i, j);

					//System.out.println(tabArray[i][j]);

				}

			}
//			for (Object[] ar : tabArray) {
//				for (Object s: ar) {
//					System.out.print(s + ",");
//				}
//				System.out.println();
//			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	
	// gets cell data
	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			cell = sheet.getRow(RowNum).getCell(ColNum);

			CellType dataType = cell.getCellTypeEnum();

			if (dataType != CellType.STRING) {

				return "";

			} else {

				String CellData = cell.getStringCellValue();

				return CellData;

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}

}
