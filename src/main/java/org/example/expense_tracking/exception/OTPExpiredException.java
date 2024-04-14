package org.example.expense_tracking.exception;

public class OTPExpiredException extends Exception{
    public OTPExpiredException(String message) {
        super(message);
    }
}
