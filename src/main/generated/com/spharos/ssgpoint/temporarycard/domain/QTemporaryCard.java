package com.spharos.ssgpoint.temporarycard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTemporaryCard is a Querydsl query type for TemporaryCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemporaryCard extends EntityPathBase<TemporaryCard> {

    private static final long serialVersionUID = 826061166L;

    public static final QTemporaryCard temporaryCard = new QTemporaryCard("temporaryCard");

    public final StringPath agency = createString("agency");

    public final StringPath birth = createString("birth");

    public final NumberPath<Integer> CVC = createNumber("CVC", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath number = createString("number");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QTemporaryCard(String variable) {
        super(TemporaryCard.class, forVariable(variable));
    }

    public QTemporaryCard(Path<? extends TemporaryCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTemporaryCard(PathMetadata metadata) {
        super(TemporaryCard.class, metadata);
    }

}

