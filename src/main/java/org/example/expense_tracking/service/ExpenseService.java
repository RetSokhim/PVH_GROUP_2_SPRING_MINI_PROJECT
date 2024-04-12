package org.example.expense_tracking.service;

import org.example.expense_tracking.model.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpense(Integer page, Integer size);
}
