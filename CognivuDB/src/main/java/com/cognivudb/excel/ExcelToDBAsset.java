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

import com.cognivudb.entity.Asset;

public class ExcelToDBAsset {
	public static boolean checkExcelFormat(MultipartFile file) {
		  
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}else {
			return false;
		}
	}
	public static List<Asset> convertExcerlToListOfProducts(InputStream is) {
		List<Asset> list = new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(is);

			XSSFSheet sheet = workbook.getSheet("asset");
			
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
				
				Asset asset=new Asset();
				
				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {
					case 0:
						asset.setAssetName(cell.getStringCellValue());
						break;

					case 1:
						asset.setManufacturer(cell.getStringCellValue());
						break;

					case 2:
						asset.setModel(cell.getStringCellValue());
						break;
						
					case 3:
						asset.setSerialNumber(cell.getStringCellValue());
						break;
						
					case 4:
						asset.setLocation(cell.getStringCellValue());
						break;
						
					case 5:
						asset.setStatus(cell.getStringCellValue());
						break;
						
					default:
						break;
					}

		          cid++;
				}

				list.add(asset);
			}

	      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
