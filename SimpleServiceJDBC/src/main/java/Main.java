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
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM courses;");

            ResultSet resultSet = statement.executeQuery("SELECT s.id, s.name, s.age FROM students s " +
                    "JOIN student_cours sc ON s.id = sc.student_id " +
                    "JOIN courses c ON sc.cours_id = c.id WHERE c.id = 1;");

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
