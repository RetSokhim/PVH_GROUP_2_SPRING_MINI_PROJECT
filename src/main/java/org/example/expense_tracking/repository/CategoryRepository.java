package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.dto.response.CategoryRespond;
import org.example.expense_tracking.model.entity.Category;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Select("""
    SELECT * FROM categories_tb;
    """)
    @Results(id = "category",value = {
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "description",column = "description"),
            @Result(property = "user",column = "user_id",
            one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
            )
    })
    List<Category> getAllCategories();

    @Select("""
    SELECT * FROM categories_tb
    WHERE  category_id = #{id}
             """)
    @ResultMap("categoryMapping")
    Category getCategoryById(Integer id);



}