package com.spharos.ssgpoint.user.application;

import com.spharos.ssgpoint.user.dto.*;

import java.util.List;

public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String UUID);

    void updateUserInfo(String UUID, UserUpdateDto userUpdateRequestDto);
    Boolean validateDuplicateLoginId(UserSignUpDto userSignUpDto); // UserSignUpDto에서 loginId만
    void resetPassword(String UUID, PasswordResetDto passwordResetDto);
    void updatePassword(String UUID, PasswordUpdateDto passwordUpdateDto);

    //void validateDuplicateBarCode(UserSignUpDto userSignUpDto);

    void updatePointPassword(String UUID, PointPasswordUpdateDto pointPasswordUpdateDto);
}
