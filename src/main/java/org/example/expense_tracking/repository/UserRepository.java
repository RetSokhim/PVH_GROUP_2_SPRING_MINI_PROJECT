package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.example.expense_tracking.model.dto.request.UserPasswordRequest;
import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.entity.User;

import java.util.UUID;

@Mapper
public interface UserRepository {
    @Select("""
    INSERT INTO user_tb(email, password, profile_image)
    VALUES (#{user.email},#{user.password},#{user.profileImage}) RETURNING *
    """)
    @Results(id = "userMapping",value = {
            @Result(column = "user_id", property = "userId", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = org.example.expense_tracking.configuration.UUIDTypeHandler.class),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "profileImage",column = "profile_image")
    })
    User createNewUser(@Param("user") UserRegisterRequest userRegisterRequest);

    @Select("""
    SELECT * FROM user_tb
    WHERE email =#{email}
    """)
    @ResultMap("userMapping")
    User findUserByEmail(String email);

    @Select("""
    SELECT * FROM user_tb
    WHERE user_id = #{userId}
    """)
    @ResultMap("userMapping")
    User findUserById(UUID userId);

    @Update("""
    UPDATE user_tb
    SET password = #{user.password}
    WHERE email = #{email}
    """)
    void resetPassword(@Param("user") UserPasswordRequest userPasswordRequest, String email);
}
