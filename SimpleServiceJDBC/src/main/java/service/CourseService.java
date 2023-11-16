package service;

import model.Course;
import java.util.List;

public interface CourseService {

    Course findById(Long id);

    Course save(Course course);

    Course update(Course course);

    void deleteById(Long id);

    List<Course> findAll();

}