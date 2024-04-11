package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.dto.request.OtpsDTO;
import org.example.expense_tracking.model.entity.Otps;

@Mapper
public interface OtpsRepository {
    @Select("""
    INSERT INTO otps_tb(otps_code, issued_at, expiration, verify, user_id) VALUES (#{user.otpsCode},#{user.issuedAt},#{user.expiration},#{user.verify},#{user.user});
    """)
    @Results(id = "otpsMapping",value = {
            @Result(property = "otpsId",column = "otps_id"),
            @Result(property = "otpsCode",column = "otps_code"),
            @Result(property = "issuedAt",column = "issued_at"),
            @Result(property = "expiration",column = "expiration"),
            @Result(property = "verify",column = "verify"),
            @Result(property = "user",column = "user_id")
    })
    void insertOtp(@Param("user") OtpsDTO otpsDTO);

    @Select("""
    SELECT * FROM otps_tb WHERE otps_code = #{verify}
    """)
    @ResultMap("otpsMapping")
    Otps selectOtpCode(Integer verify);

    @Update("""
    UPDATE otps_tb SET verify = #{verify} WHERE otps_code = #{verify}
    """)
    @ResultMap("otpsMapping")
    void confirmVerify(@Param("verify") Integer verify);

    //For resend code
    @Update("""
    UPDATE otps_tb SET otps_code = #{otps.otpsCode},issued_at = #{otps.issuedAt},expiration = #{otps.expiration} WHERE user_id = #{userId}
    """)
    @ResultMap("otpsMapping")
    void updateTheCodeAfterResend (@Param("otps") OtpsDTO otpsDTO,Integer userId);
}
