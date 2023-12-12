package repository;

import model.Course;
import model.Student;
import model.Teacher;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    Optional<Course> findById(Long id);

    void save(Course course);

    void update(Course course);

    void deleteById(Long id);

    List<Course> findAll();

    List<Student> findAllStudentsOfCourse(Long courseId);

    List<Teacher> findAllTeachersOfCourse(Long courseId);

}