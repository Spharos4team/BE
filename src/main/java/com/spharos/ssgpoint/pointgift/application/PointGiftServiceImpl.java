package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.pointgift.domain.PointGift;
import com.spharos.ssgpoint.pointgift.domain.PointGiftAccessType;
import com.spharos.ssgpoint.pointgift.domain.PointGiftType;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;
import com.spharos.ssgpoint.pointgift.infrastructure.PointGiftRepository;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointGiftServiceImpl implements PointGiftService {

    private final UserRepository userRepository;
    private final PointGiftRepository pointGiftRepository;

    // 포인트 선물 수신인 확인
    @Override
    public String getPointGiftUser(String phone, String name) {
        Optional<User> user = userRepository.findByPhoneAndName(phone, name);

        if (user.isEmpty()) {
            return "입력하신 정보로 가입된 신세계포인트 회원이 없습니다." + System.lineSeparator() + "포인트 선물하기는 신세계포인트 회원에게만 가능합니다.";
        }

        return "선물하려는 분이 맞는지 확인해 주세요.";

    }

    // 포인트 선물 보내기
    @Override
    public void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto) {
        pointGiftRepository.save(PointGift.builder()
                .point(pointGiftCreateDto.getPoint())
                .message(pointGiftCreateDto.getMessage())
                .type(PointGiftType.valueOf(pointGiftCreateDto.getType()))
                .access(PointGiftAccessType.valueOf(pointGiftCreateDto.getAccess()))
                .UUID(pointGiftCreateDto.getUUID())
                .loginId(pointGiftCreateDto.getLoginId())
                .name(pointGiftCreateDto.getName())
                .build());
    }

    // 포인트 선물 목록
    @Override
    public List<PointGiftGetDto> getPointGiftByUser(String UUID) {
        List<PointGift> pointGiftList = pointGiftRepository.findByUUID(UUID);

        return pointGiftList.stream().map(pointGift -> PointGiftGetDto.builder()
                .point(pointGift.getPoint())
                .message(pointGift.getMessage())
                .type(pointGift.getType().getValue())
                .access(pointGift.getAccess().getValue())
                .UUID(pointGift.getUUID())
                .loginId(pointGift.getLoginId())
                .name(pointGift.getName())
                .createdDate(LocalDate.from(pointGift.getCreatedDate()))
                .build()
        ).toList();
    }

}
