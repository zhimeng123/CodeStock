package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 新增文章分类
    void add(Category category);
    // 列表查询
    List<Category> list();
}
