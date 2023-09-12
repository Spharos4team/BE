package com.spharos.ssgpoint.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.core.types.dsl.PathInits;



/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -274515730L;

    public static final QEvent event = new QEvent("event");

    public final StringPath bannerUrl = createString("bannerUrl");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final SetPath<EventImage, QEventImage> eventImages = this.<EventImage, QEventImage>createSet("eventImages", EventImage.class, QEventImage.class, PathInits.DIRECT2);

    public final SetPath<EventType, EnumPath<EventType>> eventTypes = this.<EventType, EnumPath<EventType>>createSet("eventTypes", EventType.class, EnumPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> winningDate = createDateTime("winningDate", java.time.LocalDateTime.class);


    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

