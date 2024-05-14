package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleTaskStore implements TaskStore {
    private final CrudRepository crudRepository;

    @Override
    public Task save(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    @Override
    public boolean update(Task task) {
        return crudRepository.tx(session -> session.createQuery(
                        "UPDATE Task SET title = :title, description = :description, done = :done WHERE id = :id")
                .setParameter("title", task.getTitle())
                .setParameter("description", task.getDescription())
                .setParameter("done", task.isDone())
                .setParameter("id", task.getId())
                .executeUpdate() > 0);
    }

    @Override
    public boolean updateStatus(int id) {
        return crudRepository.tx(session -> session.createQuery(
                        "UPDATE Task SET done = true WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate() > 0);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.tx(session -> session.createQuery(
                        "DELETE Task WHERE id = :Id")
                .setParameter("Id", id)
                .executeUpdate() > 0);
    }

    @Override
    public Optional<Task> findById(int id) {
        return crudRepository.optional("FROM Task WHERE id = :id", Task.class,
                Map.of("id", id));
    }

    @Override
    public List<Task> getAll() {
        return crudRepository.query("FROM Task task JOIN FETCH task.priority ORDER BY task.id", Task.class);
    }

    @Override
    public List<Task> completedTasks(boolean b) {
        return crudRepository.query("FROM Task WHERE done = :d", Task.class,
                Map.of("d", b));
    }

}
