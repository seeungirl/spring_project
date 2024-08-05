package pay;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.cj.jdbc.Driver;

public class dbinfo {
	public Connection info() throws Exception {
		String db_driver= "com.mysql.cj.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/cms";
		String db_user = "hana"; 
		String db_pass = "hana1234";
		
		Class.forName(db_driver);
		Connection con = DriverManager.getConnection(db_url,db_user,db_pass);
		
		return con;
		
	}
}
