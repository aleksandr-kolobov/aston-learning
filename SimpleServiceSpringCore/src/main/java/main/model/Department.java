package main.model;

import lombok.Data;
import main.model.workers.Worker;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_depart", length = 30)
    @Size(min = 2, max = 30, message = "Короткое название отдела")
    private String nameDepart;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Worker> workers;

}