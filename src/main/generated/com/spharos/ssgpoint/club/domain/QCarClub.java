package com.spharos.ssgpoint.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarClub is a Querydsl query type for CarClub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCarClub extends EntityPathBase<CarClub> {

    private static final long serialVersionUID = -1853092978L;

    public static final QCarClub carClub = new QCarClub("carClub");

    public final StringPath areaNumber = createString("areaNumber");

    public final StringPath carFirstPart = createString("carFirstPart");

    public final StringPath carLastPart = createString("carLastPart");

    public final StringPath carMiddlePart = createString("carMiddlePart");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCarClub(String variable) {
        super(CarClub.class, forVariable(variable));
    }

    public QCarClub(Path<? extends CarClub> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarClub(PathMetadata metadata) {
        super(CarClub.class, metadata);
    }

}

