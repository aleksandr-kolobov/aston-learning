package service;

import model.Course;
import java.util.List;

public interface CourseService {

    Course findById(Long id);

    void save(Course course);

    void update(Course course);

    void deleteById(Long id);

    List<Course> findAll();

}