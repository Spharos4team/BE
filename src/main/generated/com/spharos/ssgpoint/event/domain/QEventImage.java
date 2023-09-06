package com.spharos.ssgpoint.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventImage is a Querydsl query type for EventImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventImage extends EntityPathBase<EventImage> {

    private static final long serialVersionUID = -2013731571L;

    public static final QEventImage eventImage = new QEventImage("eventImage");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageAlt = createString("imageAlt");

    public final StringPath imageUrl = createString("imageUrl");

    public QEventImage(String variable) {
        super(EventImage.class, forVariable(variable));
    }

    public QEventImage(Path<? extends EventImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventImage(PathMetadata metadata) {
        super(EventImage.class, metadata);
    }

}

