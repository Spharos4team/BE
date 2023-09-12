package com.spharos.ssgpoint.offlinepointcard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOfflinePointCard is a Querydsl query type for OfflinePointCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOfflinePointCard extends EntityPathBase<OfflinePointCard> {

    private static final long serialVersionUID = 1659675424L;

    public static final QOfflinePointCard offlinePointCard = new QOfflinePointCard("offlinePointCard");

    public final com.spharos.ssgpoint.global.QBaseEntity _super = new com.spharos.ssgpoint.global.QBaseEntity(this);

    public final StringPath alliance = createString("alliance");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> CVC = createNumber("CVC", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath number = createString("number");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath store = createString("store");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QOfflinePointCard(String variable) {
        super(OfflinePointCard.class, forVariable(variable));
    }

    public QOfflinePointCard(Path<? extends OfflinePointCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOfflinePointCard(PathMetadata metadata) {
        super(OfflinePointCard.class, metadata);
    }

}

