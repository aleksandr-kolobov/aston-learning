package repository;

import connection.DataSource;
import model.Course;
import model.Student;
import model.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository{

    @Override
    public Optional<Course> findById(Long id) {

        Optional<Course> optionalCourse = Optional.empty();

        if(id == null) {
            return optionalCourse;
        }

        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses" +
                    " WHERE id = " + id + ";");

            if (resultSet.next()) {
                Course course = new Course();
                course.setId(id);
                course.setName(resultSet.getString(2));
                course.setDuration(Integer.parseInt(resultSet.getString(3)));
                optionalCourse = Optional.of(course);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return optionalCourse;
    }

    @Override
    public void save(Course course) {
        if (course == null) {
            return;
        }
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            //course.setId(System.currentTimeMillis());
            statement.execute("INSERT INTO courses (name, duration)" +
                    " values('" + course.getName() + "', " + course.getDuration() + ");");
            statement.close();
            //return course;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Course course) {
        Long id = course.getId();
        if(id == null) {
            return;
        }
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            if (findById(id).isPresent()) {
                statement.execute("UPDATE courses SET name = '" + course.getName() +
                        "' WHERE id = " + course.getId() + ";");
                statement.execute("UPDATE courses SET duration = " + course.getDuration() +
                        " WHERE id = " + course.getId() + ";");
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        if(id == null) {
            return;
        }
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            if (findById(id).isPresent()) {
                statement.execute("DELETE FROM courses " +
                        " WHERE id = " + id + ";");
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> findAll() {

        List<Course> list = new ArrayList<>();

        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses;");

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(Long.parseLong(resultSet.getString(1)));
                course.setName(resultSet.getString(2));
                course.setDuration(Integer.parseInt(resultSet.getString(3)));
                list.add(course);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Student> findAllStudentsOfCourse(Long courseId) {

        List<Student> list = new ArrayList<>();

        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT s.id, s.name, s.age FROM students s " +
                    "JOIN student_to_course sc ON s.id = sc.student_id " +
                    "JOIN courses c ON sc.course_id = c.id WHERE c.id = " + courseId + ";");

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(Long.parseLong(resultSet.getString(1)));
                student.setName(resultSet.getString(2));
                student.setAge(Integer.parseInt(resultSet.getString(3)));
                list.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Teacher> findAllTeachersOfCourse(Long courseId) {

        List<Teacher> list = new ArrayList<>();

        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT t.id, t.name FROM teachers t " +
                    "JOIN courses c ON t.course_id = c.id WHERE c.id = " + courseId + ";");

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(Long.parseLong(resultSet.getString(1)));
                teacher.setName(resultSet.getString(2));
                teacher.setCourseId(courseId);
                list.add(teacher);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
