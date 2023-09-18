package com.spharos.ssgpoint.alliancepointcard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlliancePointCard is a Querydsl query type for AlliancePointCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlliancePointCard extends EntityPathBase<AlliancePointCard> {

    private static final long serialVersionUID = 311362254L;

    public static final QAlliancePointCard alliancePointCard = new QAlliancePointCard("alliancePointCard");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath number = createString("number");

    public final EnumPath<AlliancePointCardType> type = createEnum("type", AlliancePointCardType.class);

    public final StringPath UUID = createString("UUID");

    public QAlliancePointCard(String variable) {
        super(AlliancePointCard.class, forVariable(variable));
    }

    public QAlliancePointCard(Path<? extends AlliancePointCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlliancePointCard(PathMetadata metadata) {
        super(AlliancePointCard.class, metadata);
    }

}

