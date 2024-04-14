package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.dto.request.OtpsRequestDTO;
import org.example.expense_tracking.model.entity.Otps;

import java.util.UUID;

@Mapper
public interface OtpsRepository {
    @Select("""
            INSERT INTO otps_tb(otps_code, issued_at, expiration, user_id)
            VALUES (#{user.otpsCode},#{user.issuedAt},#{user.expiration},#{user.user});
            """)
//    @Results(id = "otpsMapping",value = {
//            @Result(property = "otpsId",column = "otps_id"),
//            @Result(property = "otpsCode",column = "otps_code"),
//            @Result(property = "issuedAt",column = "issued_at"),
//            @Result(property = "expiration",column = "expiration"),
//            @Result(property = "verify",column = "verify"),
//            @Result(property = "user",column = "user_id",
//                    one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
//            )
//    })
    void insertOtp(@Param("user") OtpsRequestDTO otpsRequestDTO);

    @Select("""
            SELECT * FROM otps_tb
            WHERE otps_code = #{otpsCode}
            """)
    @Results({
            @Result(property = "otpsId", column = "otps_id"),
            @Result(property = "otpsCode", column = "otps_code"),
            @Result(property = "issuedAt", column = "issued_at"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
            )
    })
    Otps getOtpsByCode(@Param("otpsCode")Integer otpsCode);

    @Select("""
            SELECT * FROM otps_tb
            WHERE user_id = #{userId}
            """)
    @Result(property = "otpsId", column = "otps_id")
    @Result(property = "otpsCode", column = "otps_code")
    @Result(property = "issuedAt", column = "issued_at")
    @Result(property = "user", column = "user_id",
            one = @One(select = "org.example.expense_tracking.repository.UserRepository.findUserById")
    )
    Otps getOtpsUserId(UUID userId);

    @Update("""
            UPDATE otps_tb SET verify = #{verify}
            WHERE otps_code = #{verify}
            """)
//    @ResultMap("otpsMapping")
    void confirmVerify(@Param("verify") Integer verify);

    //For resend code
    @Update("""
            UPDATE otps_tb
            SET otps_code = #{otps.otpsCode},issued_at = #{otps.issuedAt},expiration = #{otps.expiration}
            WHERE user_id = #{userId}
            """)
//    @ResultMap("otpsMapping")
    void updateTheCodeAfterResend(@Param("otps") OtpsRequestDTO otpsRequestDTO, UUID userId);
}
