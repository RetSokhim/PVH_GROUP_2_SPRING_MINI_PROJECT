package org.example.expense_tracking.service;

import org.example.expense_tracking.exception.SearchNotFoundException;
import org.example.expense_tracking.model.dto.request.ExpenseRequestDTO;
import org.example.expense_tracking.model.dto.response.ExpenseResponse;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    List<ExpenseResponse> getAllExpense(UUID userId, Integer size, Integer offset, String orderBy, String sortBy);
    ExpenseResponse insertNewExpense (ExpenseRequestDTO expenseRequestDTO) throws SearchNotFoundException;
    ExpenseResponse getExpenseById(UUID expenseId, UUID userId) throws SearchNotFoundException;
    void deleteExpenseById(UUID expenseId, UUID userId) throws SearchNotFoundException;
    ExpenseResponse updateExpenseById(UUID expenseId, ExpenseRequestDTO expenseRequestDTO, UUID userId) throws SearchNotFoundException;
    Integer getTotalExpense(UUID userId);
}
