package repository;

import model.Course;
import model.Student;
import model.Teacher;

import java.util.List;

public interface CourseRepository {

    Course save(Course course);

    boolean delete(Long id);

    Course get(Long id);

    List<Course> getList();

    List<Student> getAllStudents(Long courseId);

    List<Teacher> getAllTeachers(Long courseId);

}
