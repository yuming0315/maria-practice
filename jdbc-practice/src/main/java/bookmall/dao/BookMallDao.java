package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface BookMallDao {
	public default Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");			
			String url = "jdbc:mariadb://192.168.10.113:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url,"bookmall","bookmall");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void insert(Object vo);
	public List<Object> findAll();
	public List<Object> find(Object vo);
	public void delete(Object vo);
	public void update(Object vo);
}
