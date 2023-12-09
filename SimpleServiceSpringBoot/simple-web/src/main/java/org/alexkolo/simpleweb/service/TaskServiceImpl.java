package org.alexkolo.simpleweb.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.alexkolo.simpleweb.model.Task;
import org.alexkolo.simpleweb.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepositoryImpl;

    @Override
    public Task findById(long id) {
        log.debug("Call findById in TaskServiceImpl " + id);

        return taskRepositoryImpl.findById(id).orElse(null);
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save in TaskServiceImpl " + task);

        return taskRepositoryImpl.save(task);
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update in TaskServiceImpl " + task);

        return taskRepositoryImpl.update(task);
    }

    @Override
    public void deleteById(long id) {
        log.debug("Call deleteById in TaskServiceImpl " + id);

        taskRepositoryImpl.deleteById(id);
    }

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll in TaskServiceImpl");

        return taskRepositoryImpl.findAll();
    }

    @Override
    public void butchInsert(List<Task> tasks) {
        log.debug("Call butchInsert in TaskServiceImpl");

        taskRepositoryImpl.butchInsert(tasks);
    }
}
