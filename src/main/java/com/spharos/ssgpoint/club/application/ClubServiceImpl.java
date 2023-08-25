package com.spharos.ssgpoint.club.application;

import com.spharos.ssgpoint.club.domain.BeautyClub;
import com.spharos.ssgpoint.club.domain.BizClub;
import com.spharos.ssgpoint.club.domain.CarClub;
import com.spharos.ssgpoint.club.domain.MomKidsClub;
import com.spharos.ssgpoint.club.infrastructure.BeautyClubRepository;
import com.spharos.ssgpoint.club.infrastructure.BizClubRepository;
import com.spharos.ssgpoint.club.infrastructure.CarClubRepository;
import com.spharos.ssgpoint.club.infrastructure.MomKidsClubRepository;
import org.springframework.stereotype.Service;

@Service
public class ClubServiceImpl implements ClubService {

    private final BeautyClubRepository beautyClubRepository;
    private final BizClubRepository bizClubRepository;
    private final CarClubRepository carClubRepository;
    private final MomKidsClubRepository momKidsClubRepository;

    public ClubServiceImpl(
            BeautyClubRepository beautyClubRepository,
            BizClubRepository bizClubRepository,
            CarClubRepository carClubRepository,
            MomKidsClubRepository momKidsClubRepository) {
        this.beautyClubRepository = beautyClubRepository;
        this.bizClubRepository = bizClubRepository;
        this.carClubRepository = carClubRepository;
        this.momKidsClubRepository = momKidsClubRepository;
    }

    @Override
    public BeautyClub registerBeautyClub(BeautyClub beautyClub) {
        return beautyClubRepository.save(beautyClub);
    }

    @Override
    public BizClub registerBizClub(BizClub bizClub) {
        return bizClubRepository.save(bizClub);
    }

    @Override
    public CarClub registerCarClub(CarClub carClub) {
        return carClubRepository.save(carClub);
    }

    @Override
    public MomKidsClub registerMomKidsClub(MomKidsClub momKidsClub) {
        return momKidsClubRepository.save(momKidsClub);
    }
}
