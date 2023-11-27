package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Course extends AbstractEntity {

    private Integer duration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.LAZY)
    private List<Teacher> teachers;

}
