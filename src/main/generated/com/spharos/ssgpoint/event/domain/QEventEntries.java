package com.spharos.ssgpoint.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventEntries is a Querydsl query type for EventEntries
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventEntries extends EntityPathBase<EventEntries> {

    private static final long serialVersionUID = -1669293118L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventEntries eventEntries = new QEventEntries("eventEntries");

    public final QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isWinning = createBoolean("isWinning");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath uuid = createString("uuid");

    public QEventEntries(String variable) {
        this(EventEntries.class, forVariable(variable), INITS);
    }

    public QEventEntries(Path<? extends EventEntries> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventEntries(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventEntries(PathMetadata metadata, PathInits inits) {
        this(EventEntries.class, metadata, inits);
    }

    public QEventEntries(Class<? extends EventEntries> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event")) : null;
    }

}

