package main.model.workers;

import lombok.Data;
import main.model.Department;
import main.model.Task;
import main.model.abstractModel.AbstractWorkerFields;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "worker_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Worker")
public class Worker extends AbstractWorkerFields {

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "name_user")
    private String nameWorker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "worker_type", insertable = false, updatable = false)
    private String workerType;

    @ManyToMany(mappedBy = "workers", fetch = FetchType.EAGER)
    List<Task> tasks;
}