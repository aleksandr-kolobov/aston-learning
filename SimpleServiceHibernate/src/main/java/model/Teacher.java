package model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "course_id")
    private Integer courseId;
}