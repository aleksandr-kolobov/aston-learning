package web;

import connection.HibernateUtil;
import model.Course;
import model.Student;
import model.Teacher;
import org.hibernate.Session;
//import service.CourseService;
//import service.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/courses/*")
public class CourseServlet extends HttpServlet {

//    private CourseService courseService;
//
//    public CourseServlet () {
//        courseService = new CourseServiceImpl();
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        printWriter.write(session.get(Course.class, 1).getName() + "<br>");
        printWriter.write(session.get(Student.class, 1).getName() + "<br>");
        printWriter.write(session.get(Teacher.class, 1).getName() + "<br>");

        printWriter.close();

//        String pathInfo = req.getPathInfo();
//        resp.setContentType("text/html");
//        PrintWriter printWriter = resp.getWriter();
//
//        if (pathInfo == null) {
//            List<Course> list = courseService.findAll();
//            list.forEach(c -> printWriter.write(c.getId() + " " + c.getName() + " "
//                    + c.getDuration() + " months<br>"));
//        } else {
//            Integer id = Integer.parseInt(pathInfo.substring(1));
//            Course c = courseService.findById(id);
//            printWriter.write(c.getName() + " " + c.getDuration() + " months<br>");
//        }
//        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        Course course = new Course();
//        //  выкалупываем course из req...
//        courseService.save(course);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        Course course = new Course();
//        //  выкалупываем course из req...
//        courseService.update(course);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        String pathInfo = req.getPathInfo();
//        Integer id = Integer.parseInt(pathInfo.substring(1));
//        courseService.deleteById(id);
    }

}

