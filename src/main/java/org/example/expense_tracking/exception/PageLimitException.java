package org.example.expense_tracking.exception;

public class PageLimitException extends Exception{
    public PageLimitException(String message) {
        super(message);
    }
}
