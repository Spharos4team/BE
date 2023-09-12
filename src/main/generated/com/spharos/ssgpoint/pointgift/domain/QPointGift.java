package com.spharos.ssgpoint.pointgift.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointGift is a Querydsl query type for PointGift
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointGift extends EntityPathBase<PointGift> {

    private static final long serialVersionUID = 1144274638L;

    public static final QPointGift pointGift = new QPointGift("pointGift");

    public final com.spharos.ssgpoint.global.QBaseEntity _super = new com.spharos.ssgpoint.global.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath message = createString("message");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final EnumPath<PointGiftStatusType> status = createEnum("status", PointGiftStatusType.class);

    public final EnumPath<PointGiftType> type = createEnum("type", PointGiftType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final StringPath UUID = createString("UUID");

    public QPointGift(String variable) {
        super(PointGift.class, forVariable(variable));
    }

    public QPointGift(Path<? extends PointGift> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointGift(PathMetadata metadata) {
        super(PointGift.class, metadata);
    }

}

