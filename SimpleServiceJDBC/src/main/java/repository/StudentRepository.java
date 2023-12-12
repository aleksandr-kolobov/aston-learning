package repository;

import model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(Long id);

    void save(Student student);

    void update(Student student);

    void deleteById(Long id);

    List<Student> findAll();
}