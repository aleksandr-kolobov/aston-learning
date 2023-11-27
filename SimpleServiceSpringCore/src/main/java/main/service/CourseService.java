package main.service;

import main.model.Course;
import main.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> findById(Integer id){
        return  courseRepository.findById(id);
    }

    public void save(Course teacher) {
        if (teacher == null) return;
        Integer id = teacher.getId();
        if ((id != null) && courseRepository.findById(id).isPresent()) {
            teacher.setId(null);
        }
        courseRepository.save(teacher);
    }

    public void update(Course teacher) {
        if (teacher == null) return;
        Integer id = teacher.getId();
        if ((id == null) || courseRepository.findById(id).isEmpty()) return;
        courseRepository.save(teacher);
    }

    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }

    public List<Course> findAll(){
        return  courseRepository.findAll();
    }

}
