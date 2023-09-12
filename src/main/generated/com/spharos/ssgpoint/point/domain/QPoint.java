package com.spharos.ssgpoint.point.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoint is a Querydsl query type for Point
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoint extends EntityPathBase<Point> {

    private static final long serialVersionUID = -1272825682L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoint point1 = new QPoint("point1");

    public final com.spharos.ssgpoint.global.QBaseEntity _super = new com.spharos.ssgpoint.global.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final com.spharos.ssgpoint.pointcard.domain.QPointCard pointCard;

    public final com.spharos.ssgpoint.receipt.domain.QReceipt receipt;

    public final EnumPath<PointStatusType> statusType = createEnum("statusType", PointStatusType.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalPoint = createNumber("totalPoint", Integer.class);

    public final EnumPath<PointType> type = createEnum("type", PointType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.spharos.ssgpoint.user.domain.QUser user;

    public QPoint(String variable) {
        this(Point.class, forVariable(variable), INITS);
    }

    public QPoint(Path<? extends Point> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoint(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoint(PathMetadata metadata, PathInits inits) {
        this(Point.class, metadata, inits);
    }

    public QPoint(Class<? extends Point> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pointCard = inits.isInitialized("pointCard") ? new com.spharos.ssgpoint.pointcard.domain.QPointCard(forProperty("pointCard")) : null;
        this.receipt = inits.isInitialized("receipt") ? new com.spharos.ssgpoint.receipt.domain.QReceipt(forProperty("receipt")) : null;
        this.user = inits.isInitialized("user") ? new com.spharos.ssgpoint.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

