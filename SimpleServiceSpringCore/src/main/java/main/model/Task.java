package main.model;

import lombok.Data;
import main.model.workers.Worker;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_task")
    @Size(min = 5)
    private String nameTask;

    @Column(name = "text_task")
    @Size(min = 10)
    private String textTask;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "worker_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id")
    )
    private List<Worker> workers;

}
