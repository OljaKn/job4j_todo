package ru.job4j.todo.repository;

import org.springframework.web.servlet.tags.Param;
import ru.job4j.todo.model.Category;

import java.util.List;

public interface CategoryStore {
    List<Category> getAll();
    List<Category> findAllById(List<Integer> categoriesId);
}
