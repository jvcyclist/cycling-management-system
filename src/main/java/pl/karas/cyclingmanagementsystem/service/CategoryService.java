package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> getCategoryByName(String name);
}
