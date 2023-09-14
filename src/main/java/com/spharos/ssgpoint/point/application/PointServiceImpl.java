package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.domain.*;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.infrastructure.PointRepository;
import com.spharos.ssgpoint.point.vo.PointCreateVo;
import com.spharos.ssgpoint.point.vo.PointFilterVo;
import com.spharos.ssgpoint.pointcard.domain.PointCard;
import com.spharos.ssgpoint.pointgift.dto.PointGiftMessageDto;
import com.spharos.ssgpoint.pointgift.infrastructure.PointGiftRepository;
import com.spharos.ssgpoint.receipt.domain.Receipt;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.infrastructure.ReceiptRepository;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImpl implements PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    private final ReceiptRepository receiptRepository;
    private PointGiftRepository pointGiftRepository;

    // 포인트 생성
    @Override
    @Transactional
    public Point createPoint(String UUID, PointCreateDto pointCreateDto) {
        PointType pointType
                = new PointTypeConverter().convertToEntityAttribute(pointCreateDto.getType());
        PointStatusType pointStatusType
                = new PointStatusTypeConverter().convertToEntityAttribute(pointCreateDto.getStatusType());
        User user = userRepository.findByUuid(UUID).orElseThrow(() ->
                new IllegalArgumentException("UUID 정보 없음 = " + UUID));

        // totalPoint 계산
        Integer totalpoint = pointRepository.findTotalPoint(user.getUuid());
        Long count = pointRepository.countByUserId(user.getId());

        int calctotalPoint;

        if (count.equals(0L)) {
            calctotalPoint = pointCreateDto.getPoint();
        }
        else {
            if (pointStatusType.getCode().equals("0") || pointStatusType.getCode().equals("2")) {
                calctotalPoint= totalpoint + pointCreateDto.getPoint();
            }
            else{
                calctotalPoint= totalpoint - pointCreateDto.getPoint();
            }
        }

        // 결제적립
        if (pointCreateDto.getType().equals("1")) {


            Receipt receipt = Receipt.builder()
                    .alliance(pointCreateDto.getReceipt().getAlliance())
                    .brand(pointCreateDto.getReceipt().getBrand())
                    .storeName(pointCreateDto.getReceipt().getStoreName())
                    .number(pointCreateDto.getReceipt().getNumber())
                    .amount(pointCreateDto.getReceipt().getAmount())
                    .point(pointCreateDto.getReceipt().getReceiptPoint())
                    .cardName(pointCreateDto.getReceipt().getCardName())
                    .cardNumber(pointCreateDto.getReceipt().getCardNumber())
                    .pointCardNumber(pointCreateDto.getReceipt().getPointCardNumber())
                    .status(1)
                    .build();

            Point point = Point.builder()
                    .totalPoint(calctotalPoint)
                    .point(pointCreateDto.getPoint())
                    .title(pointCreateDto.getTitle())
                    .content(pointCreateDto.getContent())

                    .statusType(pointStatusType)
                    .type(pointType)
                    .user(user)
                    .receipt(receipt)
                    .build();
            return pointRepository.save(point);
        } else {
            Point point = Point.builder()
                    .totalPoint(calctotalPoint)
                    .point(pointCreateDto.getPoint())
                    .title(pointCreateDto.getTitle())
                    .content(pointCreateDto.getContent())
                    .statusType(pointStatusType)
                    .type(pointType)
                    .user(user)
                    .build();

            return pointRepository.save(point);
        }

    }

    // 포인트 목록
    @Override
    public List<PointGetDto> getTotalPointByUser(String UUID,Pageable page) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() ->
                new IllegalArgumentException("UUID 정보 없음 = " + UUID));

        Slice<Point> pointList = pointRepository.findByUserId(user.getUuid(),page);
        return null;

    }

    @Override
    public List<PointGetDto> getSavePointByUser(String UUID, Pageable page) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() ->
                new IllegalArgumentException("UUID 정보 없음 = " + UUID));
        Page<Point> pointList = pointRepository.findBySavePoint(user.getUuid(), page);
        return pointList.map(m -> new PointGetDto(m.getPoint(),
                m.getTitle(), m.getContent(), m.getType().getCode(), m.getStatusType().getCode(),
                m.getCreatedDate())).stream().toList();
    }


    // 포인터 내역 필터 적용
    /*@Override
    public Slice<PointFilterDto> pointFilter(Long id, String UUID, Pageable page,LocalDate startDate, LocalDate endDate, String pointUse, String pointType) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() ->
                new IllegalArgumentException("UUID 정보 없음 = " + UUID));
        return pointRepository.findByFilter(id, UUID, startDate, endDate,pointUse, pointType, page);

    }*/
    @Override
    public Page<PointFilterDto> pointFilter(String UUID, Pageable page, LocalDate startDate, LocalDate endDate, String pointUse, String pointType){
       return pointRepository.findByFilter(UUID, startDate, endDate, pointUse, pointType, page);
    }

    // 포인터 내역 필터 사용 적립 합계
    @Override
    public PointFilterSumDto sumPointsByFilter(String UUID, LocalDate startDate, LocalDate endDate, String pointUse, String pointType) {
        return pointRepository.sumPointsByFilter(UUID,pointUse,pointType,startDate,endDate);
    }

    //포인트 내역 영수증 보기
    @Override
    public ReceiptGetDto getReceiptByPointListReceiptId(Long id) {
        return receiptRepository.findReceiptById(id);
    }

    //포인트 내역 메세지 보기
    @Override
    public PointGiftMessageDto getGiftMessage(Long id){
        return pointGiftRepository.findGiftMessageById(id);
    }


}
