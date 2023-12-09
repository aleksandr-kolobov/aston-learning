package org.alexkolo.simpleweb.service;

import org.alexkolo.simpleweb.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task findById(long id);

    Task save(Task task);

    Task update(Task task);

    void deleteById(long id);

    List<Task> findAll();

    void butchInsert(List<Task> tasks);

}
