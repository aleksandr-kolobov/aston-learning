package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "students")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends AbstractEntity implements Comparable<Student> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_to_course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;

    @Override
    public int compareTo(Student s) {
        return id.compareTo(s.getId());
    }

}