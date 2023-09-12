package com.spharos.ssgpoint.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 170910798L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store1 = new QStore("store1");

    public final com.spharos.ssgpoint.alliance.domain.QAlliance alliance;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath store = createString("store");

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alliance = inits.isInitialized("alliance") ? new com.spharos.ssgpoint.alliance.domain.QAlliance(forProperty("alliance")) : null;
    }

}

