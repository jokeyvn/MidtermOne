package tw.com.company.midterm1.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tw.com.company.midterm1.Bean.MRTActivity;
import tw.com.company.midterm1.Util.MRTActivityUtil;

public class MRTActivityDaoJdbcImpl {
	private Connection conn;
	
	public void readData() throws Exception{
		URL url = new URL("https://data.taipei/api/dataset/1d45dbac-3833-4a7c-8a05-ac3272d52f72/resource/0918c007-5baa-449c-a60d-2870196e46f4/download");
		URLConnection httpConn =(URLConnection)url.openConnection();
		InputStream data= httpConn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(data, "Big5"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("c:/Action/TempData/Data.txt"));
		String line,allLine="";
		while((line=br.readLine())!=null){
			allLine += line+",";
			bw.write(line);
			bw.newLine();
		}
		String[] strData = allLine.split(",");
		
//		String createTable = "create table MRTActivity(ID int not null primary key identity(1,1),ActivityDate date,ActivityEnd date,Place nvarchar(max),Activity nvarchar(100),Organizer nvarchar(100))";
//		Statement stateTable = conn.createStatement();
//		stateTable.execute(createTable);
//		String createImageTable = "create table Image(ImageID int not null primary key identity(1,1),ImageName nvarchar(50),ImageContent varbinary(max))";
//		Statement stateTable2 = conn.createStatement();
//		stateTable.execute(createImageTable);
		
		String sqlstr = "insert MRTActivity(ActivityDate,ActivityEnd,Place,Activity,Organizer) values(?,?,?,?,?)";
		PreparedStatement state=conn.prepareStatement(sqlstr);	
		for(int i=5;i<strData.length;i++) {	
			int column = i%5+1;
			if(column<=2) {
				state.setDate(column, MRTActivityUtil.StringToDate(strData[i]));
			}else if(column<=4) {
				state.setString(column, strData[i]);
			}else if(column==5){
				state.setString(column, strData[i]);
				state.executeUpdate();	
			}
		}
		state.close();
//		stateTable2.close();
//		stateTable.close();
		bw.close();
		br.close();		
		System.out.print("資料建立成功");
	}
	
	public void insert(MRTActivity m) throws SQLException {
		String sqlstr = "insert MRTActivity(ActivityDate,ActivityEnd,Place,Activity,Organizer) values(?,?,?,?,?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setDate(1, m.getActivityDate());
		state.setDate(2, m.getActivityEnd());
		state.setString(3, m.getPlace());
		state.setString(4, m.getActivity());
		state.setString(5, m.getOrganizer());
		state.execute();
		state.close();
		System.out.print("已新增資料");
	}
	
	public void updateData(int id,String column,String content) throws SQLException {
		String sqlstr = "update MRTActivity set "+MRTActivityUtil.convert(column)+" = ? where id = ?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		if(column.equals("活動日期") || column.equals("活動結束日期")) {
			state.setDate(1, MRTActivityUtil.StringToDate(content));
		}else {
			state.setString(1, content);
		}
		state.setInt(2, id);
		state.executeUpdate();
		state.close();	
		System.out.print("已更新資料");
	}
	
	public void delete(String column,String content) throws SQLException {	
		String sqlstr = "delete MRTActivity where "+MRTActivityUtil.convert(column)+" like '%"+content+"%'";
		Statement state = conn.createStatement();
		state.execute(sqlstr);
		state.close();
		System.out.print("已刪除資料");
	}
	
	public MRTActivity queryOnce(int id) throws SQLException {
		String sqlstr = "select * from MRTActivity where id = ?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		MRTActivity m =null;
		if(rs.next()) {
			m = new MRTActivity(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6));
		}else {
			System.out.println("查無此資料");
		}
		rs.close();
		state.close();
		return m;
	}
	
	public void queryRange(String column,String content) throws SQLException {
		String sqlstr = "select * from MRTActivity where "+MRTActivityUtil.convert(column)+" like '%"+content+"%'";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sqlstr);	
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" ");
			System.out.print(rs.getDate(2)+" ");
			System.out.print(rs.getDate(3)+" ");
			System.out.print(rs.getString(4)+" ");
			System.out.print(rs.getString(5)+" ");
			System.out.print(rs.getString(6)+"\r\n");
		}
		rs.close();
		state.close();
	}
	
	public void queryAll() throws SQLException {
		String sqlstr = "select * from MRTActivity";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		ResultSet rs = state.executeQuery();
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" ");
			System.out.print(rs.getDate(2)+" ");
			System.out.print(rs.getDate(3)+" ");
			System.out.print(rs.getString(4)+" ");
			System.out.print(rs.getString(5)+" ");
			System.out.print(rs.getString(6)+"\r\n");
		}
		rs.close();
		state.close();
	}
}
