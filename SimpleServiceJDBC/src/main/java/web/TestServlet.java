package web;

import connection.DataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT s.id, s.name, s.age FROM students s " +
                    "JOIN student_to_course sc ON s.id = sc.student_id " +
                    "JOIN courses c ON sc.course_id = c.id WHERE c.id = 4;");

            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();

            while (resultSet.next()) {



                printWriter.write(resultSet.getString(1) + " "
                        + resultSet.getString(2) + " "
                        + resultSet.getString(3) + "<br>");
            }
            printWriter.close();
            resultSet.close();
            statement.close();
            DataSource.closeConnection();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }
}
