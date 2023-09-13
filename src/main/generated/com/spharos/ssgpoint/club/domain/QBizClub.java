package com.spharos.ssgpoint.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBizClub is a Querydsl query type for BizClub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBizClub extends EntityPathBase<BizClub> {

    private static final long serialVersionUID = 1790792013L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBizClub bizClub = new QBizClub("bizClub");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final QClubList clubList;

    public final StringPath companyAddress = createString("companyAddress");

    public final StringPath companyName = createString("companyName");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath representativeName = createString("representativeName");

    public QBizClub(String variable) {
        this(BizClub.class, forVariable(variable), INITS);
    }

    public QBizClub(Path<? extends BizClub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBizClub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBizClub(PathMetadata metadata, PathInits inits) {
        this(BizClub.class, metadata, inits);
    }

    public QBizClub(Class<? extends BizClub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubList = inits.isInitialized("clubList") ? new QClubList(forProperty("clubList")) : null;
    }

}

