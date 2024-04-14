package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.request.OtpsRequestDTO;
import org.example.expense_tracking.model.entity.Otps;
import org.example.expense_tracking.repository.OtpsRepository;
import org.example.expense_tracking.service.OtpsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class OtpsServiceImpl implements OtpsService {
    private final OtpsRepository otpsRepository;
    public OtpsServiceImpl(OtpsRepository otpsRepository) {
        this.otpsRepository = otpsRepository;
    }

    @Override
    public OtpsRequestDTO generateOtp() {
        OtpsRequestDTO otps = new OtpsRequestDTO();
        Random random = new Random();
        int randomNum = random.nextInt(999999);
        StringBuilder otp = new StringBuilder(Integer.toString(randomNum));
        while (otp.length() < 6) {
            otp.insert(0, "0");
        }
        otps.setIssuedAt(Timestamp.valueOf(LocalDateTime.now()));
        Integer sentOtp = Integer.parseInt(otp.toString());
        otps.setOtpsCode(sentOtp);
        otps.setExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)));
        return otps;
    }

    @Override
    public Otps getOtpByUserId(UUID userId) {
        return otpsRepository.getOtpsUserId(userId);
    }

    @Override
    public void insertOtp(OtpsRequestDTO otpsRequestDTO) {
        otpsRepository.insertOtp(otpsRequestDTO);
    }

    @Override
    public Otps getOtpsByCode(Integer otpsCode) {
        return otpsRepository.getOtpsByCode(otpsCode);
    }

    @Override
    public Otps getOtpsUserId(UUID userId) {
        return otpsRepository.getOtpsUserId(userId);
    }

    @Override
    public void confirmVerify(Integer verify) {
        otpsRepository.confirmVerify(verify);
    }

    @Override
    public void updateTheCodeAfterResend(OtpsRequestDTO otpsRequestDTO, UUID userId) {
        otpsRepository.updateTheCodeAfterResend(otpsRequestDTO, userId);
    }
}
