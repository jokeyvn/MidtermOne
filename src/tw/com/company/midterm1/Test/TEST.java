package tw.com.company.midterm1.Test;

import java.util.Scanner;

import tw.com.company.midterm1.Bean.MRTActivity;
import tw.com.company.midterm1.DAO.CreateImageDaoJdbcImpl;
import tw.com.company.midterm1.DAO.MRTActivityDaoJdbcImpl;
import tw.com.company.midterm1.Factory.CreateImageDaoFactory;
import tw.com.company.midterm1.Factory.MRTActivityDaoFactory;
import tw.com.company.midterm1.Util.ConnectionUtil;

public class TEST {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ConnectionUtil.createSQLConn();
		MRTActivityDaoJdbcImpl dao = MRTActivityDaoFactory.createMRTActivityDao();
		CreateImageDaoJdbcImpl dao2 = CreateImageDaoFactory.CreateImageDao();
		
		Scanner input = new Scanner(System.in);

		Out:while (true) {
			System.out.println("\n\n請輸入要進行的操作：(0)關閉系統,(1)建立資料,(2)新增資料,(3)修改資料,(4)查詢資料,(5)刪除資料,(6)儲存圖片,(7)匯出圖檔");
			switch (input.nextInt()) {
			case 0:
				System.out.println("系統結束");
				break Out;
			case 1:
				System.out.println("將資料輸入進資料庫");
				dao.readData();
				break;
			case 2:
				System.out.println("請輸入新增的資料：(1)活動日期、(2)活動結束日期、(3)活動地點、(4)活動名稱、(5)主辦單位");
				MRTActivity m = new MRTActivity(input.next(), input.next(), input.next(), input.next(),input.next());
				dao.insert(m);
				break;
			case 3:
				System.out.println("請輸入要修改的資料id：");
				int updateID = input.nextInt();
				System.out.println("請輸入要修改的欄位和內容：");
				dao.updateData(updateID, input.next(), input.next());
				break;
			case 4:
				System.out.println("請選擇：(1)單筆資料查詢、(2)範圍查詢、(3)全部查詢");
				int choose = input.nextInt();
				switch(choose){
					case 1:
						System.out.print("請輸入資料ID：");
						MRTActivity m2 = dao.queryOnce(input.nextInt());
						System.out.println(m2);
						break;
					case 2:
						System.out.println("請輸入資料欄位以及相關內容");
						dao.queryRange(input.next(), input.next());
						break;
					case 3:
						dao.queryAll();
						break;
					default:
						System.out.println("輸入錯誤");
						break;
				}
				break;
			case 5:
				System.out.println("請輸入欄位名稱和要刪除的相關資料：");
				dao.delete(input.next(), input.next());
				break;
			case 6:
				System.out.println("請輸入要保存的圖片名稱，以及圖片位址");
				dao2.insertImage(input.next(), input.next());
				break;
			case 7:
				System.out.println("請輸入要建立的圖片ID");
				dao2.createImage(input.nextInt());
				break;
			default:
				System.out.print("輸入錯誤，請重新輸入");
				break;
			}
		}
		ConnectionUtil.closeSQLConn();
	}
}
