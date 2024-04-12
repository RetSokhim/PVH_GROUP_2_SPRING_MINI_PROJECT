package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.entity.Category;


import java.util.List;

@Mapper
public interface CategoryRepository {
    @Select("""
    SELECT * FROM categories_tb
    LIMIT #{size}
    Offset #{size} * (#{page}-1)
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
    @ResultMap("category")
    Category getCategoryById(Integer id);

    @Select("""
        INSERT INTO categories_tb( name, description)
        VALUES ( #{categories.name}, #{categories.description})
        RETURNING *;
        """)
    @ResultMap("category")
    Category insertNewCategory(@Param("categories")CategoryDTO categories);


//    UpdateCategoryById
    @Select("""
        UPDATE categories_tb
        SET name = #{categories.name}, description= #{categories.description}
        WHERE category_id = #{id}
        """)
    Category updateCategoryById(Integer id,@Param("categories")CategoryDTO categories);

    //DeleteCategoryByID
    @Select("""
    DELETE FROM categories_tb
    WHERE category_id = #{id}
    RETURNING *
    """)
    @ResultMap("category")
    Category deleteCategoryById(Integer id);







}