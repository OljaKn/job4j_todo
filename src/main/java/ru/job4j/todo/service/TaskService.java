package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task save(Task task);
    boolean update(Task task);
    boolean updateStatus(int id);
    boolean delete(int id);
    Optional<Task> findById(int id);
    List<Task> getAll();
    List<Task> completedTasks(boolean b);
}
