package main.service;

import main.model.Teacher;
import main.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Optional<Teacher> findById(Integer id){
        return  teacherRepository.findById(id);
    }

    public void save(Teacher teacher) {
        if (teacher == null) return;
        Integer id = teacher.getId();
        if ((id != null) && teacherRepository.findById(id).isPresent()) {
            teacher.setId(null);
        }
        teacherRepository.save(teacher);
    }

    public void update(Teacher teacher) {
        if (teacher == null) return;
        Integer id = teacher.getId();
        if ((id == null) || teacherRepository.findById(id).isEmpty()) return;
        teacherRepository.save(teacher);
    }

    public void delete(Integer id) {
        teacherRepository.deleteById(id);
    }

    public List<Teacher> findAll(){
        return  teacherRepository.findAll();
    }

}
