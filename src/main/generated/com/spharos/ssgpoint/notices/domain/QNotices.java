package com.spharos.ssgpoint.notices.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotices is a Querydsl query type for Notices
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotices extends EntityPathBase<Notices> {

    private static final long serialVersionUID = -436271282L;

    public static final QNotices notices = new QNotices("notices");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> regDate = createDateTime("regDate", java.util.Date.class);

    public final StringPath title = createString("title");

    public QNotices(String variable) {
        super(Notices.class, forVariable(variable));
    }

    public QNotices(Path<? extends Notices> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotices(PathMetadata metadata) {
        super(Notices.class, metadata);
    }

}

