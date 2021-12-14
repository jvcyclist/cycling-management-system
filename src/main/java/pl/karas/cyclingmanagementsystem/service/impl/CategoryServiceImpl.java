package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Category;
import pl.karas.cyclingmanagementsystem.repository.CategoryRepository;
import pl.karas.cyclingmanagementsystem.service.CategoryService;

import javax.persistence.Access;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
}
