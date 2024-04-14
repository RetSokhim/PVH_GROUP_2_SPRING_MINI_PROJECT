package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.entity.Expense;

import java.util.List;

@Mapper
public interface ExpenseRepository {
    @Select("""
    SELECT * FROM expense_tb
    LIMIT #{size}
    Offset #{size} * (#{page}-1)
    """)
    @Results(id = "expense",value = {
            @Result(property = "expenseId",column = "expense_id"),
            @Result(property = "amount",column = "amount"),
            @Result(property = "date", column = "date"),
            @Result(property = "description",column = "description"),
            @Result(property = "user", column = "user_id",
            one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
            ),
            @Result(property = "category", column = "category_id",
             one = @One(select = "org.example.expense_tracking.repository.CategoryRepository.getCategoryById")
            )
    })
    List<Expense> getAllExpense();

}
