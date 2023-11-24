package service;

import model.Course;
import model.Student;
import java.util.List;

public interface StudentService {

    Student findById(Integer id);

    void save(Student student);

    void update(Student student);

    void deleteById(Integer id);

    List<Student> findAll();

    List<Course> findAllCoursesOfStudent(Integer studentId);

}