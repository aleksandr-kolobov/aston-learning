package service;

import model.Course;
import model.Student;
import repository.CourseRepository;
import repository.CourseRepositoryImpl;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository = new CourseRepositoryImpl();

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id);
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
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Student> findAllStudentsOfCourse(Integer courseId) {
        return courseRepository.findAllStudentsOfCourse(courseId);
    }
}
