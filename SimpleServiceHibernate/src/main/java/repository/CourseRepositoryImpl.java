package repository;

import connection.HibernateUtil;
import model.Course;
import model.Student;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository{

    @Override
    public Course findById(Integer id) {
        if (id == null) {
            return null;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = session.get(Course.class, id);
        session.close();
        return course;
    }

    @Override
    public void save(Course course) {
        if (course == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(course);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Course course) {
        if (course == null) {
            return;
        }
        Integer id = course.getId();
        if (id == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Course oldCourse = session.get(Course.class, id);
        if (oldCourse != null) {
            session.update(course);
        }
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Course course = session.get(Course.class, id);
        if (course != null) {
            session.delete(course);
        }
        tx1.commit();
        session.close();
    }

    @Override
    public List<Course> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = (List<Course>) session.createQuery("From Course").list();
        session.close();
        return courses;
    }

    @Override
    public List<Student> findAllStudentsOfCourse(Integer courseId) {

        List<Student> list = new ArrayList<>();

        Student studentVasya = new Student();
        studentVasya.setId(0);
        studentVasya.setName("Vasya");
        studentVasya.setAge(courseId);
        list.add(studentVasya);
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
