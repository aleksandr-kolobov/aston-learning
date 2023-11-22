package web;

import model.Course;
import service.CourseService;
import service.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/courses/*")
public class CourseServlet extends HttpServlet {

    private final CourseService courseService;

    public CourseServlet () {
        courseService = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (pathInfo == null) {
            List<Course> list = courseService.findAll();
            list.forEach(c -> printWriter.write(c.getId() + " " + c.getName() + " "
                    + c.getDuration() + " months<br>"));
        } else {
            Integer id = Integer.parseInt(pathInfo.substring(1));
            Course course = courseService.findById(id);
            printWriter.write(course.getId() + " " + course.getName() + " "
                    + course.getDuration() + " months<br> has teachers:<br>");
            course.getTeachers().forEach(t -> printWriter.write(t.getName() + "<br>"));
        }

        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Course course = new Course();
        String name = req.getParameter("name");
        int duration = Integer.parseInt(req.getParameter("duration"));
        course.setName(name);
        course.setDuration(duration);
        courseService.save(course);
        resp.sendRedirect("/courses");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Course course = new Course();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int duration = Integer.parseInt(req.getParameter("duration"));
        course.setId(id);
        course.setName(name);
        course.setDuration(duration);
        courseService.update(course);
        resp.sendRedirect("/courses");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        courseService.deleteById(id);
        resp.sendRedirect("/courses");
    }

}

