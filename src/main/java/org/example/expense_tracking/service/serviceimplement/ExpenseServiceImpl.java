package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.request.ExpenseRequest;
import org.example.expense_tracking.model.dto.response.ExpenseDto;
import org.example.expense_tracking.model.entity.Expense;
import org.example.expense_tracking.repository.ExpenseRepository;
import org.example.expense_tracking.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = expenseRepository.postUser(expenseDto);
        return modelMapper.map(expense, ExpenseDto.class);
    }
}
