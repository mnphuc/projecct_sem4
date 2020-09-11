package com.project.sem4.repository.interfaces;

import com.project.sem4.model.BlogCategories;
import com.project.sem4.model.Blogs;
import com.project.sem4.model.view.BlogView;

import java.util.List;

public interface BlogRepository {
    public List<BlogCategories> getAllBlogCategory();
    public Boolean insertBlogCategory(BlogCategories blogCategories);
    public BlogCategories findBlogCategoryById(Integer id);
    public Boolean editBlogCategory(BlogCategories blogCategories);
    public Boolean deleteBlogCategory(Integer id);
    public List<BlogView> getAllBlog();
    public Boolean insertBlog(Blogs blogs);
    public Blogs findBlogById(Integer id);
    public Boolean editBlog(Blogs blogs);
    public Boolean deleteBlog(Integer id);
    public BlogView getBlogBySlug(String slug);
}
