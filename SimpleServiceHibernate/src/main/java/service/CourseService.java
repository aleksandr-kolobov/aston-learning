package service;

import model.Course;
import model.Student;

import java.util.List;

public interface CourseService {

    Course findById(Integer id);

    void save(Course course);

    void update(Course course);

    void deleteById(Integer id);

    List<Course> findAll();

    public List<Student> findAllStudentsOfCourse(Integer courseId);

}