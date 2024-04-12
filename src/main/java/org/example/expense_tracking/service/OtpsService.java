package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.OtpsRequest;
import org.example.expense_tracking.model.entity.Otps;

public interface OtpsService {
    OtpsRequest generateOtp ();
    Otps getOtpByUserId(Integer userId);
}
