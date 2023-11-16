package web;

import model.Course;
import repository.CourseRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CourseRepositoryImpl courseRepository = new CourseRepositoryImpl();

        List<Course> list = courseRepository.findAll();
        /*List<Course> list = new ArrayList<>();
        Course course = new Course();
        course.setId(6L);
        course.setName("Mechanics");
        course.setDuration(19);
        courseRepository.update(course);
        list.add(course);*/

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        list.forEach(c -> printWriter.write(c.getId() + " " + c.getName() + " "
                + c.getDuration() + " months<br>"));

        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }

}
