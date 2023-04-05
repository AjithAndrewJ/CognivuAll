package com.cognivudb.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.cognivudb.entity.Component;

public class ExcelToDBComponent {
	
	public static boolean checkExcelFormat(MultipartFile file) {
		  
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}else {
			return false;
		}
	}
	public static List<Component> convertExcerlToListOfProducts(InputStream is) {
		List<Component> list = new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(is);

			XSSFSheet sheet = workbook.getSheet("component");
			
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();
	
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells= row.iterator();

				int cid = 0;
				
				Component component=new Component();
				
				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {
					case 0:
						component.setComponentType(cell.getStringCellValue());
						break;

					case 1:
						component.setManufacturer(cell.getStringCellValue());
						break;

					case 2:
						component.setModel(cell.getStringCellValue());
						break;
						
					case 3:
						component.setSerialNumber(cell.getStringCellValue());
						break;
						
					case 4:
						component.setCapacity(cell.getStringCellValue());
						break;
					
					default:
						break;
					}

		          cid++;
				}

				list.add(component);
			}

	      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
