package com.spharos.ssgpoint.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventImageList is a Querydsl query type for EventImageList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventImageList extends EntityPathBase<EventImageList> {

    private static final long serialVersionUID = 1742322507L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventImageList eventImageList = new QEventImageList("eventImageList");

    public final QEvent event;

    public final QEventImage eventImage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEventImageList(String variable) {
        this(EventImageList.class, forVariable(variable), INITS);
    }

    public QEventImageList(Path<? extends EventImageList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventImageList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventImageList(PathMetadata metadata, PathInits inits) {
        this(EventImageList.class, metadata, inits);
    }

    public QEventImageList(Class<? extends EventImageList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event")) : null;
        this.eventImage = inits.isInitialized("eventImage") ? new QEventImage(forProperty("eventImage")) : null;
    }

}

