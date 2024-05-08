package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskStore;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final TaskStore taskStore;

    @Override
    public Task save(Task task) {
        return taskStore.save(task);
    }

    @Override
    public boolean update(Task task) {
        return taskStore.update(task);
    }

    @Override
    public boolean delete(int id) {
       return taskStore.delete(id);
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    @Override
    public List<Task> getAll() {
        return taskStore.getAll();
    }

    @Override
    public List<Task> completedTasks(boolean b) {
        return taskStore.completedTasks(b);
    }
}
