package com.spharos.ssgpoint.user.application;

import com.spharos.ssgpoint.user.dto.password.PasswordUpdateDto;
import com.spharos.ssgpoint.user.dto.shoppinghistory.*;
import com.spharos.ssgpoint.user.dto.user.*;

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

    PointGetDto getPoint(String UUID);

    UserSavePointDto getSavePoint(String UUID);
    UserUsePointDto getUsePoint(String UUID);

    VisitedCountDto getVisitedCount(String UUID);

    TotalPointDtoByReceipt getTotalPoint(String UUID);


    List<FrequentBrandTop3CountDto> getFrequentBrandTop3Count(String UUID);
    List<FrequentBrandTop3SumDto> getFrequentBrandTop3Sum(String UUID);
}