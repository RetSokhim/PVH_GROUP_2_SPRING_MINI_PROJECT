package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.entity.Category;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Select("""
    SELECT * FROM categories_tb
    LIMIT  #{size}
    OFFSET #{size} * (#{page} - 1)
    """)
    @Results(id = "CategoryMapping",value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "org.example.expense_tracking.repository.UserRepository.getUserById")
            )
    })
    List<Category> getAllCategories(Integer page, Integer size);

    @Select("SELECT * FROM categories_tb WHERE category_id = #{categoryId}")
    @ResultMap("CategoryMapping")
    Category getCategoryById(Integer categoryId);

    @Delete("DELETE FROM categories_tb WHERE category_id = #{categoryId}")
    void deleteCategoryById(Integer eventId);

}
