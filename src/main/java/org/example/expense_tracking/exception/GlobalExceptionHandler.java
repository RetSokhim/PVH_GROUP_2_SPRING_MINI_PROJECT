package org.example.expense_tracking.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //For handle with argument exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException (MethodArgumentNotValidException e){
        HashMap<String,String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Invalid Input Please Try Again !");
        problemDetail.setTitle("BAD_REQUEST");
        problemDetail.setProperty("Time Stamp", LocalDateTime.now());
        problemDetail.setProperty("Errors",errors);
        return problemDetail;
    }
    //For catch search not found exception
    @ExceptionHandler(SearchNotFoundException.class)
    public ProblemDetail searchNotFoundException (SearchNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                e.getMessage());
        problemDetail.setTitle("NOT_FOUND");
        problemDetail.setProperty("Time Stamp",LocalDateTime.now());
        return problemDetail;
    }
    //For catch limit page exception
    @ExceptionHandler(PageLimitException.class)
    public ProblemDetail pageLimitException (PageLimitException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                e.getMessage());
        problemDetail.setTitle("BAD_REQUEST");
        problemDetail.setProperty("Time Stamp",LocalDateTime.now());
        return problemDetail;
    }
    //For catch @Positive Message Exception
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail constraintViolationException (ConstraintViolationException e){
        HashMap<String,String> errors = new HashMap<>();
        for(ConstraintViolation<?> error : e.getConstraintViolations()){
            errors.put(error.getPropertyPath().toString().substring(error.
                                    getPropertyPath().
                                    toString().
                                    indexOf(".")+1).
                            replaceFirst("\\.",""),
                    error.getMessage());
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Invalid Input");
        problemDetail.setTitle("BAD_REQUEST");
        problemDetail.setProperty("Time Stamp",LocalDateTime.now());
        problemDetail.setProperty("Errors",errors);
        return problemDetail;
    }
}
