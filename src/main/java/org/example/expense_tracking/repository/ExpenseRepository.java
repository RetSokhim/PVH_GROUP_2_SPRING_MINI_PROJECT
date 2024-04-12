package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.dto.request.ExpenseRequest;
import org.example.expense_tracking.model.dto.response.ExpenseDto;
import org.example.expense_tracking.model.entity.Expense;

@Mapper
public interface ExpenseRepository {
    @Select("""
            INSERT INTO expense_tb(amount, description, date, user_id, category_id)\s
            VALUES (#{expense.amount}, #{expense.description}, #{expense.date}, #{expense.user_id}, #{expense.category_id})
            RETURNING *
           \s""")
    @Result(property = "amount", column = "amount")
    @Result(property = "description", column = "description")
    @Result(property = "date", column = "date")
    @Result(property = "userId", column = "user_id")
    @Result(property = "categoryId", column = "category_id")
    Expense postUser(@Param("expense")ExpenseDto expenseDto);
}
