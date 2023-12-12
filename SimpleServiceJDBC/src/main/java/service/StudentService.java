package service;

import model.Student;
import java.util.List;

public interface StudentService {

    Student findById(Long id);

    void save(Student student);

    void update(Student student);

    void deleteById(Long id);

    List<Student> findAll();
}