package org.example.expense_tracking.service;

import java.util.UUID;

public interface ExpenseService {
    void deleteExpenseByID(UUID expenseId);
}
