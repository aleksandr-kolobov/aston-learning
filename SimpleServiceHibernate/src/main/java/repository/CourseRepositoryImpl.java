package repository;

import connection.HibernateUtil;
import model.Course;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseRepositoryImpl implements CourseRepository{

    @Override
    public Course findById(Integer id) {
        if (id == null) {
            return null;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Course.class, id);
    }

    @Override
    public void save(Course course) {
        if (course == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(course);
        tx1.commit();
    }

    @Override
    public void update(Course course) {
        if (course == null) {
            return;
        }
        Integer id = course.getId();
        if (id == null) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Course updatingCourse = session.get(Course.class, id);
        if (updatingCourse != null) {
            updatingCourse.setName(course.getName());
            updatingCourse.setDuration(course.getDuration());
            session.save(updatingCourse);
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
        Course course = session.get(Course.class, id);
        if (course != null) {
            session.delete(course);
        }
        tx1.commit();
    }

    @Override
    public List<Course> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (List<Course>) session.createQuery("From Course").list();
    }

    @Override
    public List<Teacher> findAllTeachersOfCourse(Integer courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Course.class, courseId).getTeachers();
    }

}
