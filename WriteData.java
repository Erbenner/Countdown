package com.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteData {

	public WriteData() {

	}

	public static boolean write(Data data) {
		try {

			/* 读取文档 */
			HSSFWorkbook workbook = new HSSFWorkbook(
					new FileInputStream(new File("D:/workspace/data.xls")));

			/* 读取第一个工作表 */
			HSSFSheet sheet = workbook.getSheetAt(0);

			
			/* 得到最后一行 */
			int maxRow = sheet.getLastRowNum();

			/* 在最后一行新建一行 */
			HSSFRow row = sheet.createRow(maxRow+1);

			/* 在最后一行中创建四个单元格 */
			HSSFCell event = row.createCell((short)0);
			HSSFCell year = row.createCell((short)1);
			HSSFCell month = row.createCell((short)2);
			HSSFCell day = row.createCell((short)3);
			data.format();
			event.setCellValue(data.event);
			year.setCellValue(data.year);
			month.setCellValue(data.month);
			day.setCellValue(data.day);

			FileOutputStream out = new FileOutputStream("D:/workspace/data.xls");
			workbook.write(out);
			out.flush();
			out.close();
			workbook.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
