package service;

import model.Course;
import model.Student;
import repository.CourseRepository;
import repository.CourseRepositoryImpl;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository = new CourseRepositoryImpl();

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.update(course);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);

    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Student> findAllStudentsOfCourse(Long courseId) {
        return courseRepository.findAllStudentsOfCourse(courseId);
    }
}
