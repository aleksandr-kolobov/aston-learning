package main.service;

import main.model.Student;
import main.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findById(Integer id){
        return  studentRepository.findById(id);
    }

    public void save(Student teacher) {
        if (teacher == null) return;
        Integer id = teacher.getId();
        if ((id != null) && studentRepository.findById(id).isPresent()) {
            teacher.setId(null);
        }
        studentRepository.save(teacher);
    }

    public void update(Student teacher) {
        if (teacher == null) return;
        Integer id = teacher.getId();
        if ((id == null) || studentRepository.findById(id).isEmpty()) return;
        studentRepository.save(teacher);
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findAll(){
        return  studentRepository.findAll();
    }

}