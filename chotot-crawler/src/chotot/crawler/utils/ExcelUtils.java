package chotot.crawler.utils;

import chotot.crawler.entities.ApartmentInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author chungnt
 */
public class ExcelUtils {
	public static final ExcelUtils INSTANCE = new ExcelUtils();

	public int writeFileExcel(String fileName, String sheetName, List<String> headers, List<ApartmentInfo> listApartments) {
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet(sheetName);
		 sheet.setColumnWidth(0, 4000);
		 sheet.setColumnWidth(1, 6000);
		_buildHeader(workbook, sheet, headers);
		_buildContent(workbook, sheet, listApartments);

		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	private void _buildHeader(Workbook workbook, Sheet sheet, List<String> headers) {
		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		headerStyle.setFont(font);

		int idxCell = 0;
		for (String colHeaderName : headers) {
			Cell headerCell = header.createCell(idxCell);
			headerCell.setCellValue(colHeaderName);
			headerCell.setCellStyle(headerStyle);
			idxCell++;
		}
	}

	private void _buildContent(Workbook workbook, Sheet sheet, List<ApartmentInfo> listApartments) {
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		int rowIdx = 1; // header: 0
		// apartId, subject, areaId, areaName, subRegionId, subRegionName, price, numRooms, numToilets, ereaSquare, categoryId, categoryName
		// phone, longitude, latitude, streetId, streetNumber, address, wardId, wardName, regionId, regionName, body
		for (ApartmentInfo apartmentInfo : listApartments) {
			Row row = sheet.createRow(rowIdx);
			_buildRowData(row, style, apartmentInfo);
			rowIdx++;
		}
	}

	private void _buildRowData(Row row, CellStyle cellStyle, ApartmentInfo apartmentInfo) {
		Cell cell0 = row.createCell(0);
		cell0.setCellValue(apartmentInfo.getApartId());
		cell0.setCellStyle(cellStyle);

		Cell cell1 = row.createCell(1);
		cell1.setCellValue(apartmentInfo.getSubject());
		cell1.setCellStyle(cellStyle);

		Cell cell2 = row.createCell(2);
		cell2.setCellValue(apartmentInfo.getAreaId());
		cell2.setCellStyle(cellStyle);

		Cell cell3 = row.createCell(3);
		cell3.setCellValue(apartmentInfo.getAreaName());
		cell3.setCellStyle(cellStyle);

		Cell cell4 = row.createCell(4);
		cell4.setCellValue(apartmentInfo.getSubRegionId());
		cell4.setCellStyle(cellStyle);

		Cell cell5 = row.createCell(5);
		cell5.setCellValue(apartmentInfo.getSubRegionName());
		cell5.setCellStyle(cellStyle);

		Cell cell6 = row.createCell(6);
		cell6.setCellValue(apartmentInfo.getPrice());
		cell6.setCellStyle(cellStyle);

		Cell cell7 = row.createCell(7);
		cell7.setCellValue(apartmentInfo.getNumRooms());
		cell7.setCellStyle(cellStyle);

		Cell cell8 = row.createCell(8);
		cell8.setCellValue(apartmentInfo.getNumToilets());
		cell8.setCellStyle(cellStyle);

		Cell cell9 = row.createCell(9);
		cell9.setCellValue(apartmentInfo.getEreaSquare());
		cell9.setCellStyle(cellStyle);

		Cell cell10 = row.createCell(10);
		cell10.setCellValue(apartmentInfo.getCategoryId());
		cell10.setCellStyle(cellStyle);

		Cell cell11 = row.createCell(11);
		cell11.setCellValue(apartmentInfo.getCategoryName());
		cell11.setCellStyle(cellStyle);

		// phone, longitude, latitude, streetId, streetNumber, address, wardId, wardName, regionId, regionName, body
		Cell cell12 = row.createCell(12);
		cell12.setCellValue(apartmentInfo.getPhone());
		cell12.setCellStyle(cellStyle);

		Cell cell13 = row.createCell(13);
		cell13.setCellValue(apartmentInfo.getLongitude());
		cell13.setCellStyle(cellStyle);

		Cell cell14 = row.createCell(14);
		cell14.setCellValue(apartmentInfo.getLatitude());
		cell14.setCellStyle(cellStyle);

		Cell cell15 = row.createCell(15);
		cell15.setCellValue(apartmentInfo.getStreetId());
		cell15.setCellStyle(cellStyle);

		Cell cell16 = row.createCell(16);
		cell16.setCellValue(apartmentInfo.getStreetNumber());
		cell16.setCellStyle(cellStyle);

		Cell cell17 = row.createCell(17);
		cell17.setCellValue(apartmentInfo.getAddress());
		cell17.setCellStyle(cellStyle);

		Cell cell18 = row.createCell(18);
		cell18.setCellValue(apartmentInfo.getWardId());
		cell18.setCellStyle(cellStyle);

		Cell cell19 = row.createCell(19);
		cell19.setCellValue(apartmentInfo.getWardName());
		cell19.setCellStyle(cellStyle);

		Cell cell20 = row.createCell(20);
		cell20.setCellValue(apartmentInfo.getRegionId());
		cell20.setCellStyle(cellStyle);

		Cell cell21 = row.createCell(21);
		cell21.setCellValue(apartmentInfo.getRegionName());
		cell21.setCellStyle(cellStyle);

//		Cell cell22 = row.createCell(22);
//		cell22.setCellValue(apartmentInfo.getBody());
//		cell22.setCellStyle(cellStyle);
	}
}
