package com.spharos.ssgpoint.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserClub is a Querydsl query type for UserClub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserClub extends EntityPathBase<UserClub> {

    private static final long serialVersionUID = -1840298307L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserClub userClub = new QUserClub("userClub");

    public final QClubList clubList;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.spharos.ssgpoint.user.domain.QUser UUID;

    public QUserClub(String variable) {
        this(UserClub.class, forVariable(variable), INITS);
    }

    public QUserClub(Path<? extends UserClub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserClub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserClub(PathMetadata metadata, PathInits inits) {
        this(UserClub.class, metadata, inits);
    }

    public QUserClub(Class<? extends UserClub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clubList = inits.isInitialized("clubList") ? new QClubList(forProperty("clubList")) : null;
        this.UUID = inits.isInitialized("UUID") ? new com.spharos.ssgpoint.user.domain.QUser(forProperty("UUID"), inits.get("UUID")) : null;
    }

}

