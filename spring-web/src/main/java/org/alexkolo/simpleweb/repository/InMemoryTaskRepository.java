package org.alexkolo.simpleweb.repository;

import lombok.extern.slf4j.Slf4j;
import org.alexkolo.simpleweb.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class InMemoryTaskRepository implements TaskRepository{

    private final List<Task> tasks = new ArrayList<>();
    @Override
    public Optional<Task> findById(long id) {
        log.debug("Call findById InMemoryTaskRepository " + id);

        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save InMemoryTaskRepository " + task);

        task.setId(System.currentTimeMillis());
        tasks.add(task);

        return task;
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update InMemoryTaskRepository " + task);

        Task existedTask = findById(task.getId()).orElse(null);
        if (existedTask != null) {
            existedTask.setTitle(task.getTitle());
            existedTask.setDescription(task.getDescription());
            existedTask.setPriority(task.getPriority());
        }
        return existedTask;
    }

    @Override
    public void deleteById(long id) {
        log.debug("Call deleteById InMemoryTaskRepository " + id);

        findById(id).ifPresent(tasks::remove);
    }

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll InMemoryTaskRepository");

        return tasks;
    }

    @Override
    public void butchInsert(List<Task> tasks) {
        log.debug("Call butchInsert InMemoryTaskRepository");

        this.tasks.addAll(tasks);
    }
}
