package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.OtpsDTO;
import org.example.expense_tracking.model.entity.Otps;

public interface OtpsService {
    OtpsDTO generateOtp ();
}
