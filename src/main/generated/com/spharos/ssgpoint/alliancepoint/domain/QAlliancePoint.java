package com.spharos.ssgpoint.alliancepoint.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlliancePoint is a Querydsl query type for AlliancePoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlliancePoint extends EntityPathBase<AlliancePoint> {

    private static final long serialVersionUID = 1589019694L;

    public static final QAlliancePoint alliancePoint = new QAlliancePoint("alliancePoint");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final EnumPath<AlliancePointType> type = createEnum("type", AlliancePointType.class);

    public final StringPath UUID = createString("UUID");

    public QAlliancePoint(String variable) {
        super(AlliancePoint.class, forVariable(variable));
    }

    public QAlliancePoint(Path<? extends AlliancePoint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlliancePoint(PathMetadata metadata) {
        super(AlliancePoint.class, metadata);
    }

}

