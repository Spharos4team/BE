package com.spharos.ssgpoint.club.application;

import com.spharos.ssgpoint.club.domain.BeautyClub;
import com.spharos.ssgpoint.club.domain.BizClub;
import com.spharos.ssgpoint.club.domain.CarClub;
import com.spharos.ssgpoint.club.domain.MomKidsClub;

public interface ClubService {
    BeautyClub registerBeautyClub(BeautyClub beautyClub);
    BizClub registerBizClub(BizClub bizClub);
    CarClub registerCarClub(CarClub carClub);
    MomKidsClub registerMomKidsClub(MomKidsClub momKidsClub);
}
