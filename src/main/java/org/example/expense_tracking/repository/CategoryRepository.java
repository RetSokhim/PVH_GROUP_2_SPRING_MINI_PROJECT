package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.entity.Category;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Select("""
    SELECT * FROM categories_tb WHERE user_id = #{userId};
    """)
    @Results(id = "category",value = {
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "description",column = "description"),
            @Result(property = "user",column = "user_id",
            one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
            )
    })
    List<Category> getAllCategories(Integer userId);

    @Select("""
    SELECT * FROM categories_tb WHERE category_id = #{id} AND user_id = #{userId}
    """)
    @ResultMap("category")
    Category getCategoryById(Integer id, Integer userId);

    @Select("""
    DELETE FROM categories_tb WHERE category_id = #{id} AND user_id = #{userId}
    """)
    @ResultMap("category")
    void deleteCategoryById(Integer id, Integer userId);

    @Select("""
    INSERT INTO categories_tb(name, description, user_id) VALUES (#{categories.name}, #{categories.description}, #{categories.user.userId}) RETURNING *
    """)
    @ResultMap("category")
    Category insertNewCategory(@Param("categories") Category category);

    @Select("""
    UPDATE categories_tb SET name = #{categories.name},description = #{categories.description} WHERE category_id = #{id} AND user_id = #{userId} RETURNING *
    """)
    @ResultMap("category")
    Category updateCategoryById(Integer id,@Param("categories") CategoryDTO categoryDTO,Integer userId);
}