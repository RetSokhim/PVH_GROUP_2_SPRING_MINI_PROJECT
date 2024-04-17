package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.OtpsRequestDTO;
import org.example.expense_tracking.model.entity.Otps;

import java.util.UUID;

public interface OtpsService {
    OtpsRequestDTO generateOtp ();
    Otps getOtpByUserId(UUID userId);
    void insertOtp(OtpsRequestDTO otpsRequestDTO);
    Otps getOtpsByCode(Integer otpsCode);
    Otps getOtpsUserId(UUID userId);
    void confirmVerify(Integer verify);
    void updateTheCodeAfterResend (OtpsRequestDTO otpsRequestDTO, UUID userId);
}
