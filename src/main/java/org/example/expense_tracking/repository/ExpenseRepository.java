package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface ExpenseRepository {
    @Results(id = "expenseMapping", value = {
            @Result(property = "expenseId", column = "expense_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "date", column = "date"),
            @Result(property = "user",column = "user_id",
            one = @One(select = "org.example.expense_tracking.repository.CategoryRepository.getCategoryById")
            )
    })
    void deleteExpenseById(UUID expenseId);
}
