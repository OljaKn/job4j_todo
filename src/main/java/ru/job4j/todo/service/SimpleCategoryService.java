package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryStore;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {
    private final CategoryStore categoryStore;

    @Override
    public List<Category> getAll() {
        return categoryStore.getAll();
    }

    @Override
    public List<Category> findAllById(List<Integer> categoriesId) {
        return categoryStore.findAllById(categoriesId);
    }
}
