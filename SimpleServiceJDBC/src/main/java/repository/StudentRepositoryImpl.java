package repository;

import connection.DataSource;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Optional<Student> findById(Long id) {

        Optional<Student> optionalStudent = Optional.empty();

        if(id == null) {
            return optionalStudent;
        }

        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students" +
                    " WHERE id = " + id + ";");

            if (resultSet.next()) {
                Student student = new Student();
                student.setId(id);
                student.setName(resultSet.getString(2));
                student.setAge(Integer.parseInt(resultSet.getString(3)));
                optionalStudent = Optional.of(student);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return optionalStudent;
    }

    @Override
    public void save(Student student) {
        if (student == null) {
            return;
        }
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO students (name, age)" +
                    " values('" + student.getName() + "', " + student.getAge() + ");");
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        if(student == null) {
            return;
        }
        Long id = student.getId();
        if(id == null) {
            return;
        }
        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            if (findById(id).isPresent()) {
                statement.execute("UPDATE students SET name = '" + student.getName() +
                        "' WHERE id = " + student.getId() + ";");
                statement.execute("UPDATE students SET age = " + student.getAge() +
                        " WHERE id = " + student.getId() + ";");
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
                statement.execute("DELETE FROM students " +
                        " WHERE id = " + id + ";");
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {

        List<Student> list = new ArrayList<>();

        try {
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students;");

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
}