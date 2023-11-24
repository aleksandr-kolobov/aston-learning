package web;

import model.Student;
import service.CourseService;
import service.CourseServiceImpl;
import service.StudentService;
import service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/students/*")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentServlet () {
        studentService = new StudentServiceImpl();
        courseService = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (pathInfo == null) {
            List<Student> list = studentService.findAll();
            list.forEach(s -> printWriter.write(s.getId() + " " + s.getName() + " "
                    + s.getAge() + " years<br>"));
        } else {
            Integer id = Integer.parseInt(pathInfo.substring(1));
            Student student = studentService.findById(id);
            printWriter.write(student.getId() + " "  + student.getName() + " "
                    + student.getAge() + " years<br> has courses:<br>");
            student.getCourses().forEach(c -> printWriter.write(c.getName() + "<br>"));
        }

        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Student student = new Student();
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        student.setName(name);
        student.setAge(age);
        studentService.save(student);
        resp.sendRedirect("/students");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Student student = new Student();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        studentService.update(student);
        resp.sendRedirect("/students");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        studentService.deleteById(id);
        resp.sendRedirect("/students");
    }

}