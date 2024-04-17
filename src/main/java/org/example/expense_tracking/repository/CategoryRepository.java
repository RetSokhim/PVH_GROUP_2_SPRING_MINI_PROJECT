package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.configuration.UUIDTypeHandler;
import org.example.expense_tracking.model.dto.request.CategoryRequestDTO;
import org.example.expense_tracking.model.entity.Category;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CategoryRepository {
    @Select("""
    SELECT * FROM categories_tb
    WHERE user_id = #{userId} LIMIT #{size} OFFSET #{offset}
    """)
    @Results(id = "category",value = {
            @Result(property = "categoryId",column = "category_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "name",column = "name"),
            @Result(property = "description",column = "description"),
            @Result(property = "user",column = "user_id",
            one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
            )
    })
    List<Category> getAllCategories(UUID userId,Integer size,Integer offset);

    @Select("""
    SELECT * FROM categories_tb
    WHERE category_id = #{categoryId}
    AND user_id = #{userId}
    """)
    @ResultMap("category")
    Category getCategoryById(UUID categoryId, UUID userId);

    @Select("""
    DELETE FROM categories_tb
    WHERE category_id = #{categoryId} AND user_id = #{userId}
    """)
    @ResultMap("category")
    void deleteCategoryById(UUID categoryId, UUID userId);

    @Select("""
    INSERT INTO categories_tb(name, description, user_id)
    VALUES (#{categories.name}, #{categories.description}, #{categories.user.userId}) RETURNING *
    """)
    @ResultMap("category")
    Category insertNewCategory(@Param("categories") Category category);

    @Select("""
    UPDATE categories_tb
    SET name = #{categories.name},description = #{categories.description}
    WHERE category_id = #{categoryId}
    AND user_id = #{userId} RETURNING *
    """)
    @ResultMap("category")
    Category updateCategoryById(UUID categoryId, @Param("categories") CategoryRequestDTO categoryRequestDTO, UUID userId);
    @Select("""
    SELECT * FROM categories_tb
    WHERE category_id = #{categoryId}
    """)
    @ResultMap("category")
    Category findCategoryById(UUID categoryId);

    @Select("""
    SELECT COUNT(*) FROM categories_tb WHERE user_id = #{userId}
    """)
    Integer getTotalCategory(UUID userId);
}