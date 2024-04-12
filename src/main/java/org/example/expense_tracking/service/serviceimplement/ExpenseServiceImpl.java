package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.entity.Expense;
import org.example.expense_tracking.repository.ExpenseRepository;
import org.example.expense_tracking.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAllExpense(Integer page, Integer size) {
        return expenseRepository.getAllExpense();
    }
}

