package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.request.OtpsRequest;
import org.example.expense_tracking.model.entity.Otps;
import org.example.expense_tracking.repository.OtpsRepository;
import org.example.expense_tracking.service.OtpsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import org.example.expense_tracking.model.dto.request.OtpsRequest;
import org.example.expense_tracking.model.entity.Otps;
import org.example.expense_tracking.repository.OtpsRepository;
import org.example.expense_tracking.service.OtpsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpsServiceImpl implements OtpsService {
    private final OtpsRepository otpsRepository;
    public OtpsServiceImpl(OtpsRepository otpsRepository) {
        this.otpsRepository = otpsRepository;
    }

    //generate OTP
    @Override
    public OtpsRequest generateOtp() {
        OtpsRequest otps = new OtpsRequest();
        Random random = new Random();
        int randomNum = random.nextInt(999999);
        String otp = Integer.toString(randomNum);
        while (otp.length() < 6){
            otp = "0" + otp;
        }
        otps.setIssuedAt(Timestamp.valueOf(LocalDateTime.now()));
        Integer sentOtp = Integer.parseInt(otp);
        otps.setOtpsCode(sentOtp);
        otps.setExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)));
        return otps;
    }

    @Override
    public Otps getOtpByUserId(Integer userId) {
        return otpsRepository.getOtpsUserId(userId);
    }

    @Override
    public void insertOtp(OtpsRequest otpsRequest) {
        otpsRepository.insertOtp(otpsRequest);
    }

    @Override
    public Otps getOtpsByCode(Integer otpsCode) {
        return otpsRepository.getOtpsByCode(otpsCode);
    }

    @Override
    public Otps getOtpsUserId(Integer userId) {
        return otpsRepository.getOtpsUserId(userId);
    }

    @Override
    public void confirmVerify(Integer verify) {
        otpsRepository.confirmVerify(verify);
    }

    @Override
    public void updateTheCodeAfterResend(OtpsRequest otpsRequest, Integer userId) {
        otpsRepository.updateTheCodeAfterResend(otpsRequest,userId);
    }
}
