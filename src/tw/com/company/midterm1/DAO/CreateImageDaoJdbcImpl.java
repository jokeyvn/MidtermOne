package tw.com.company.midterm1.DAO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateImageDaoJdbcImpl {
	private Connection conn;
	public void insertImage(String name, String pUrl) throws Exception {
		URL url = new URL(pUrl);
		URLConnection httpConn =(URLConnection)url.openConnection();
		BufferedInputStream bis = new BufferedInputStream(httpConn.getInputStream());
		String sqlstr = "insert Image values(?,?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, name);
		state.setBinaryStream(2, bis);
		state.execute();
		bis.close();
		state.close();
		System.out.print("圖片已保存");
	}
	
	public void createImage(int id) throws SQLException, IOException {
		String sqlstr = "select * from Image where ImageID = ?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		if(rs.next()) {
			Blob blob = rs.getBlob(3);
			String name = rs.getString(2);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("c:/Action/TempImage/"+name+".jpg"));
			bos.write(blob.getBytes(1,(int)blob.length()));
			bos.flush();
			bos.close();
		}
		rs.close();
		state.close();
		System.out.print("圖片建立完成");
	}

}
