package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.configuration.UUIDTypeHandler;
import org.example.expense_tracking.model.dto.request.ExpenseRequestDTO;
import org.example.expense_tracking.model.entity.Expense;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ExpenseRepository {
    @Select("""
    SELECT * FROM expense_tb
    WHERE user_id = #{userId}
    ORDER BY ${orderBy} ${sortBy}
    LIMIT #{size} OFFSET #{offset}
    """)
    @Results(id = "expense",value = {
            @Result(property = "expenseId",column = "expense_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "amount",column = "amount"),
            @Result(property = "date", column = "date"),
            @Result(property = "description",column = "description"),
            @Result(property = "user", column = "user_id",
            one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
            ),
            @Result(property = "category", column = "category_id",
             one = @One(select = "org.example.expense_tracking.repository.CategoryRepository.findCategoryById")
            )
    })
    List<Expense> getAllExpense(UUID userId,Integer size,Integer offset,String orderBy,String sortBy);
    @Select("""
    INSERT INTO expense_tb(amount, description, user_id, category_id)
    VALUES (#{expenses.amount},#{expenses.description},#{expenses.user.userId},#{expenses.category.categoryId}) RETURNING *
    """)
    @ResultMap("expense")
    Expense insertNewExpense(@Param("expenses") Expense expense);
    @Select("""
    SELECT * FROM expense_tb
    WHERE expense_id = #{expenseId} AND user_id = #{userId}
    """)
    @ResultMap("expense")
    Expense getExpenseById(UUID expenseId, UUID userId);
    @Delete("""
    DELETE FROM expense_tb
    WHERE expense_id = #{expenseId} AND user_id = #{userId}
    """)
    @ResultMap("expense")
    void deleteExpenseById(UUID expenseId, UUID userId);
    @Select("""
    UPDATE expense_tb SET amount = #{expenses.amount},description = #{expenses.description},date = #{expenses.date},category_id = #{expenses.categoryId}
    WHERE expense_id = #{expenseId} AND user_id = #{userId} RETURNING *
    """)
    @ResultMap("expense")
    Expense updateExpenseById(UUID expenseId, @Param("expenses") ExpenseRequestDTO expenseRequestDTO, UUID userId);

    @Select("""
    SELECT COUNT(*) FROM expense_tb WHERE user_id = #{userId}
    """)
    Integer getTotalExpense(UUID userId);
}
