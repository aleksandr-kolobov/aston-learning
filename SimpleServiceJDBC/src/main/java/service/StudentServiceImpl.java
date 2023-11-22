package service;

import model.Student;
import repository.StudentRepository;
import repository.StudentRepositoryImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository = new StudentRepositoryImpl();

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}