import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/college";
        String user = "postgres";
        String pass = "testtest";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            //statement.execute(";");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " "
                        + resultSet.getString(2) + " "
                        + resultSet.getString(3));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
