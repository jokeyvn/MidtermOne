package tw.com.company.midterm1.Util;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class MRTActivityUtil {
	
	private static Map<String,String> columnConvert = new HashMap<>();
	
	static {
		columnConvert.put("活動日期", "ActivityDate");
		columnConvert.put("活動結束日期", "ActivityEnd");
		columnConvert.put("活動地點", "Place");
		columnConvert.put("活動名稱", "Activity");
		columnConvert.put("主辦單位", "Organizer");
	}
	
	public static String convert(String key) {
		return columnConvert.getOrDefault(key, "找不到此欄位");
	}
	
	public static Date StringToDate(String date) {
		date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
		return Date.valueOf(date);
	}
//	public static String show(List<MRTActivity> list){
//		return list.toString().replaceAll("[\\[\\]]","").replace(", ", "\r\n");
//	}
}
