package com.spharos.ssgpoint.user.application;

import com.spharos.ssgpoint.term.domain.UserTermList;
import com.spharos.ssgpoint.user.dto.*;

import java.util.List;
import java.util.Map;

public interface UserService {
    void createUser(UserSignUpDto userSignUpDto);
    UserGetDto getUserByLoginId(String loginId);
    UserGetDto getUserByUUID(String UUID);

    void updateUserInfo(String UUID, UserUpdateDto userUpdateRequestDto);
    void validateDuplicateLoginId(UserSignUpDto userSignUpDto); // UserSignUpDto에서 loginId만

    void updatePassword(String UUID, PasswordUpdateDto passwordUpdateDto);

    Map<String,Boolean> getTerm(String UUID);
    Map<String,Boolean> updateTerm(String UUID, TermUpdateDto termUpdateDto);

    void softDeleteUser(String UUID);




}