package org.alexkolo.simpleweb.repository;

import org.alexkolo.simpleweb.exception.TaskNotFoundException;
import org.alexkolo.simpleweb.model.Task;
import org.alexkolo.simpleweb.repository.mapper.TaskRowMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Primary
@Repository
@RequiredArgsConstructor
public class DatabaseTaskRepository implements TaskRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Task> findById(long id) {
        log.debug("Call findById in DatabaseTaskRepository " + id);

        String sql = "SELECT * FROM tasks WHERE id = ?";
        Task task = DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new TaskRowMapper(), 1)));

        return Optional.ofNullable(task);
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save in DatabaseTaskRepository " + task);

        task.setId(System.currentTimeMillis());
        String sql = "INSERT INTO tasks(id, title, description, priority) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getId(), task.getTitle(),
                                  task.getDescription(), task.getPriority());
        return task;
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update in DatabaseTaskRepository " + task);

        Task existedTask = findById(task.getId()).orElse(null);
        if (existedTask != null) {
            String sql = "UPDATE tasks SET title = ?, description = ?, priority = ? WHERE id = ?";
            jdbcTemplate.update(sql, task.getTitle(), task.getDescription(),
                                      task.getPriority(), task.getId());
            return task;
        }
        throw new TaskNotFoundException();
    }

    @Override
    public void deleteById(long id) {
        log.debug("Call deleteById in DatabaseTaskRepository " + id);

        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll in DatabaseTaskRepository");

        String sql = "SELECT * FROM tasks";

        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    @Override
    public void butchInsert(List<Task> tasks) {
        log.debug("Call butchInsert in DatabaseTaskRepository");

        String sql = "INSERT INTO tasks(id, title, description, priority) VALUES(?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Task task = tasks.get(i);
                ps.setLong(1, task.getId());
                ps.setString(2, task.getTitle());
                ps.setString(3, task.getDescription());
                ps.setInt(4, task.getPriority());
            }

            @Override
            public int getBatchSize() {
                return tasks.size();
            }
        });

    }
}
