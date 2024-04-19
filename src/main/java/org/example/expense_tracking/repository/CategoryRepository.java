package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.expense_tracking.model.dto.response.CategoryResponse;

@Mapper
public interface CategoryRepository {

    @Select("""
    SELECT * FROM categories_tb WHERE category_id = #{category_id}
    """)
    CategoryResponse getCategoryById(Integer categoryId);
}
