package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Categories;

import java.util.List;

public interface    CategoriesRepository {
    public List<Categories> getAllCategories();
    public Boolean insertCategories(Categories categories);
    public Boolean updateCategories(Categories categories);
    public Categories findCategoryById(Integer id);
    public Boolean deleteCategories(Integer id);
}
