import java.sql.*;
import javax.swing.*;

public class sqliteConnection
{
//	Connection conn = null;
	
	/*
	 *
	 * @wbp.parser.entryPoint
	 */
	
	public String s = " connection class ";
	
	
	
	public static Connection dbConnector()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Database.db");
			//JOptionPane.showMessageDialog(null, "Connection is successful..!!");
			//conn.commit();
			System.out.println("Conn object used in  : " + conn);
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
