package com.spharos.ssgpoint.alliance.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlliance is a Querydsl query type for Alliance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlliance extends EntityPathBase<Alliance> {

    private static final long serialVersionUID = -1830914988L;

    public static final QAlliance alliance1 = new QAlliance("alliance1");

    public final StringPath alliance = createString("alliance");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAlliance(String variable) {
        super(Alliance.class, forVariable(variable));
    }

    public QAlliance(Path<? extends Alliance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlliance(PathMetadata metadata) {
        super(Alliance.class, metadata);
    }

}

