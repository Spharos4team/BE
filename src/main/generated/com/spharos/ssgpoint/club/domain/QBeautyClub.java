package com.spharos.ssgpoint.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBeautyClub is a Querydsl query type for BeautyClub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBeautyClub extends EntityPathBase<BeautyClub> {

    private static final long serialVersionUID = 1372795886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBeautyClub beautyClub = new QBeautyClub("beautyClub");

    public final QClubList clubList;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBeautyClub(String variable) {
        this(BeautyClub.class, forVariable(variable), INITS);
    }

    public QBeautyClub(Path<? extends BeautyClub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBeautyClub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBeautyClub(PathMetadata metadata, PathInits inits) {
        this(BeautyClub.class, metadata, inits);
    }

    public QBeautyClub(Class<? extends BeautyClub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubList = inits.isInitialized("clubList") ? new QClubList(forProperty("clubList")) : null;
    }

}

