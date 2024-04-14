package org.example.expense_tracking.service;

import org.apache.ibatis.annotations.Param;
import org.example.expense_tracking.model.dto.request.OtpsRequest;
import org.example.expense_tracking.model.entity.Otps;

public interface OtpsService {
    OtpsRequest generateOtp ();
    Otps getOtpByUserId(Integer userId);
    void insertOtp(OtpsRequest otpsRequest);
    Otps getOtpsByCode(Integer otpsCode);
    Otps getOtpsUserId(Integer userId);
    void confirmVerify(Integer verify);
    void updateTheCodeAfterResend (OtpsRequest otpsRequest, Integer userId);
}
