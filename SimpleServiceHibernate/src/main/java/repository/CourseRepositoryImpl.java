package repository;

import connection.HibernateUtil;
import model.Course;
import model.Student;
import model.Teacher;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository{

    @Override
    public Course findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = session.get(Course.class, id);
        session.close();
        return course;
    }

    @Override
    public void save(Course course) {
        return;
    }

    @Override
    public void update(Course course) {
        return;
    }

    @Override
    public void deleteById(Integer id) {
        return;
    }

    @Override
    public List<Course> findAll() {

        List<Course> list = new ArrayList<>();
/*
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses;");

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(Integer.parseInt(resultSet.getString(1)));
                course.setName(resultSet.getString(2));
                course.setDuration(Integer.parseInt(resultSet.getString(3)));
                list.add(course);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
        return list;
    }

    @Override
    public List<Student> findAllStudentsOfCourse(Integer courseId) {

        List<Student> list = new ArrayList<>();
/*
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT s.id, s.name, s.age FROM students s " +
                    "JOIN student_to_course sc ON s.id = sc.student_id " +
                    "JOIN courses c ON sc.course_id = c.id WHERE c.id = " + courseId + ";");

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(Integer.parseInt(resultSet.getString(1)));
                student.setName(resultSet.getString(2));
                student.setAge(Integer.parseInt(resultSet.getString(3)));
                list.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
        return list;
    }

    @Override
    public List<Teacher> findAllTeachersOfCourse(Integer courseId) {

        List<Teacher> list = new ArrayList<>();
/*
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT t.id, t.name FROM teachers t " +
                    "JOIN courses c ON t.course_id = c.id WHERE c.id = " + courseId + ";");

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(Integer.parseInt(resultSet.getString(1)));
                teacher.setName(resultSet.getString(2));
                teacher.setCourseId(courseId);
                list.add(teacher);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
        return list;
    }
}
