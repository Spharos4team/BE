package com.spharos.ssgpoint.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMomKidsClub is a Querydsl query type for MomKidsClub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMomKidsClub extends EntityPathBase<MomKidsClub> {

    private static final long serialVersionUID = 1516277714L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMomKidsClub momKidsClub = new QMomKidsClub("momKidsClub");

    public final QClubList clubList;

    public final DatePath<java.time.LocalDate> firstChildBirthDate = createDate("firstChildBirthDate", java.time.LocalDate.class);

    public final StringPath firstChildGender = createString("firstChildGender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> secondChildBirthDate = createDate("secondChildBirthDate", java.time.LocalDate.class);

    public final StringPath secondChildGender = createString("secondChildGender");

    public QMomKidsClub(String variable) {
        this(MomKidsClub.class, forVariable(variable), INITS);
    }

    public QMomKidsClub(Path<? extends MomKidsClub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMomKidsClub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMomKidsClub(PathMetadata metadata, PathInits inits) {
        this(MomKidsClub.class, metadata, inits);
    }

    public QMomKidsClub(Class<? extends MomKidsClub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubList = inits.isInitialized("clubList") ? new QClubList(forProperty("clubList")) : null;
    }

}

