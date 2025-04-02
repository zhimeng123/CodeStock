package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service // 把实现类对象交给IOS容器
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        // 新增文章分类
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        // 列表查询
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");  // 查到当前用户ID
        return categoryMapper.list(userid);
    }
}
