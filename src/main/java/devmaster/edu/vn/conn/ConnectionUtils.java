package devmaster.edu.vn.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getSQLServerConnection()
            throws SQLException, ClassNotFoundException {
        String serverName = "ADMIN\\SQLEXPRESS";
        String databaseName = "Lab04JspServletJDBC"; // Thay thế bằng tên cơ sở dữ liệu thực tế của bạn
        String url = "jdbc:sqlserver://" + serverName + ";databaseName=" + databaseName + ";integratedSecurity=true;";

        // Load JDBC driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Establish the connection
        Connection connection = DriverManager.getConnection(url);

        return connection;
    }

	public static Connection getMSSQLConnection() {
		// TODO Auto-generated method stub
		return null;
	}
}
