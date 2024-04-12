package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.ExpenseRequest;
import org.example.expense_tracking.model.dto.response.ExpenseDto;
import org.example.expense_tracking.model.entity.Expense;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);
}
