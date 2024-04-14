package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.request.ExpenseRequestDTO;
import org.example.expense_tracking.model.dto.response.CategoryExpenseResponse;
import org.example.expense_tracking.model.dto.response.ExpenseResponse;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.model.entity.Category;
import org.example.expense_tracking.model.entity.Expense;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.repository.CategoryRepository;
import org.example.expense_tracking.repository.ExpenseRepository;
import org.example.expense_tracking.repository.UserRepository;
import org.example.expense_tracking.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ExpenseResponse> getAllExpense(UUID userId, Integer size, Integer offset, String orderBy, String sortBy) {
        List<Expense> expenses = expenseRepository.getAllExpense(userId,size,offset,orderBy,sortBy);
        List<ExpenseResponse> expenseResponses = new ArrayList<>();
        for(Expense expense : expenses){
            ExpenseResponse expenseResponse = modelMapper.map(expense,ExpenseResponse.class);
            expenseResponses.add(expenseResponse);
        }
        return expenseResponses;
    }

    @Override
    public ExpenseResponse insertNewExpense(ExpenseRequestDTO expenseRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        Category category = categoryRepository.getCategoryById(expenseRequestDTO.getCategoryId(),user.getUserId());

        Expense expense = new Expense();
        expense.setAmount(expenseRequestDTO.getAmount());
        expense.setDescription(expenseRequestDTO.getDescription());
        expense.setDate(LocalDateTime.now());
        expense.setUser(user);
        expense.setCategory(category);
        Expense expenseAfterInsertIntoDatabase = expenseRepository.insertNewExpense(expense);

        UserRegisterResponse userRegisterResponse = modelMapper.map(user,UserRegisterResponse.class);
        CategoryExpenseResponse categoryExpenseResponse = modelMapper.map(category,CategoryExpenseResponse.class);
        ExpenseResponse expenseResponse = modelMapper.map(expense,ExpenseResponse.class);
        expenseResponse.setCategory(categoryExpenseResponse);
        expenseResponse.setUser(userRegisterResponse);
        expenseResponse.setExpenseId(expenseAfterInsertIntoDatabase.getExpenseId());
        return expenseResponse;
    }

    @Override
    public ExpenseResponse getExpenseById(UUID expenseId, UUID userId) {
        Expense expense = expenseRepository.getExpenseById(expenseId,userId);
        return modelMapper.map(expense,ExpenseResponse.class);
    }

    @Override
    public void deleteExpenseById(UUID expenseId, UUID userId) {
        expenseRepository.deleteExpenseById(expenseId,userId);
    }

    @Override
    public ExpenseResponse updateExpenseById(UUID expenseId, ExpenseRequestDTO expenseRequestDTO, UUID userId) {
        Expense expense = expenseRepository.updateExpenseById(expenseId, expenseRequestDTO,userId);
        return modelMapper.map(expense,ExpenseResponse.class);
    }
}

