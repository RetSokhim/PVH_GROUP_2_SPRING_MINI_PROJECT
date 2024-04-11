package org.example.expense_tracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.entity.User;

@Mapper
public interface UserRepository {
    @Select("""
    INSERT INTO user_tb(email, password, profile_image) VALUES (#{user.email},#{user.password},#{user.profileImage}) RETURNING *
    """)
    @Results(id = "userMapping",value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "profileImage",column = "profile_image")
    })
    User createNewUser(@Param("user") UserRegisterRequest userRegisterRequest);

    @Select("""
    SELECT * FROM user_tb WHERE email =#{email}
    """)
    @ResultMap("userMapping")
    User findUserByEmail(String email);
}
