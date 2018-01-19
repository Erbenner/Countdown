package com.data;

import java.io.File;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class ReadData {

	public static ArrayList<Data> spider(String path) {
		ArrayList<Data> list = new ArrayList<Data>();
		try {
			Workbook book = Workbook.getWorkbook(new File(path));
			Sheet sheet = book.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				Data data = new Data();
				data.event = sheet.getCell(0, i).getContents();
				data.year = sheet.getCell(1, i).getContents();
				data.month = sheet.getCell(2, i).getContents();
				data.day = sheet.getCell(3, i).getContents();
				list.add(data);
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	public static String dataToStr(Data temp){
//		String str="111";
//		str=temp.event + "：" + temp.year + "年"
//				+ temp.month + "月" + temp.day + "日";
//		return str;
//	}
}
