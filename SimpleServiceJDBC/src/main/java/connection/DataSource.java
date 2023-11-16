package connection;

import java.sql.*;

public class DataSource {

    private static Connection connection = null;

    public static synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/college";
            String user = "postgres";
            String pass = "testtest";
            connection = DriverManager.getConnection(url, user, pass);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
