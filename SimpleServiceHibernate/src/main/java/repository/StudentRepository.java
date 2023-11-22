package repository;

import model.Student;

import java.util.List;

public interface StudentRepository {

    Student findById(Integer id);

    void save(Student student);

    void update(Student student);

    void deleteById(Integer id);

    List<Student> findAll();
}