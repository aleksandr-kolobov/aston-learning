package repository;

import model.Student;

import connection.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Student findById(Integer id) {
        if (id == null) {
            return null;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    @Override
    public void save(Student student) {
        if (student == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(student);
        tx1.commit();
        session.close();
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
        Student oldStudent = session.get(Student.class, id);
        if (oldStudent != null) {
            session.update(student);
        }
        tx1.commit();
        session.close();
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
            session.delete(student);
        }
        tx1.commit();
        session.close();
    }

    @Override
    public List<Student> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = (List<Student>) session.createQuery("From Student").list();
        session.close();
        return students;
    }
}
