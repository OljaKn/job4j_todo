package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;


import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class SimpleCategoryStore implements CategoryStore {
    private final CrudRepository crudRepository;

    @Override
    public List<Category> getAll() {
        return crudRepository.query("FROM Category ORDER BY id", Category.class);
    }

    @Override
    public List<Category> findAllById(List<Integer> categoriesId) {
        return crudRepository.query("FROM Category WHERE id IN :id", Category.class,
                Map.of("id", categoriesId));
    }
}
