package repository;

import model.Course;
import model.Student;

import connection.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Student findById(Integer id) {
        if (id == null) {
            return null;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Student.class, id);
    }

    @Override
    public void save(Student student) {
        if (student == null) {
            return;
        }
        student.setId(null);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(student);
        tx1.commit();
    }

    @Override
    public void update(Student student) {
        if (student == null) {
            return;
        }
        Integer id = student.getId();
        if (id == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        if (session.get(Student.class, id) != null) {
            session.merge(student);
        }
        tx1.commit();
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
        }
        tx1.commit();
    }

    @Override
    public List<Student> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("SELECT a FROM Student a",
                Student.class).getResultList();
        Collections.sort(students);
        return students;
    }

    public List<Course> findAllCoursesOfStudent(Integer studentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Student.class, studentId).getCourses();
    }

}
