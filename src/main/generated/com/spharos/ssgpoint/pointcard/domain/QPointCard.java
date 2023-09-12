package com.spharos.ssgpoint.pointcard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointCard is a Querydsl query type for PointCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointCard extends EntityPathBase<PointCard> {

    private static final long serialVersionUID = 798898894L;

    public static final QPointCard pointCard = new QPointCard("pointCard");

    public final com.spharos.ssgpoint.global.QBaseEntity _super = new com.spharos.ssgpoint.global.QBaseEntity(this);

    public final StringPath agency = createString("agency");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath number = createString("number");

    public final EnumPath<PointCardType> type = createEnum("type", PointCardType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final StringPath UUID = createString("UUID");

    public QPointCard(String variable) {
        super(PointCard.class, forVariable(variable));
    }

    public QPointCard(Path<? extends PointCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointCard(PathMetadata metadata) {
        super(PointCard.class, metadata);
    }

}

