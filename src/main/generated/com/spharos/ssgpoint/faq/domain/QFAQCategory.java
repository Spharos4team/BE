package com.spharos.ssgpoint.faq.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFAQCategory is a Querydsl query type for FAQCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFAQCategory extends EntityPathBase<FAQCategory> {

    private static final long serialVersionUID = -775056884L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFAQCategory fAQCategory = new QFAQCategory("fAQCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QFAQCategory parent;

    public final StringPath title = createString("title");

    public QFAQCategory(String variable) {
        this(FAQCategory.class, forVariable(variable), INITS);
    }

    public QFAQCategory(Path<? extends FAQCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFAQCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFAQCategory(PathMetadata metadata, PathInits inits) {
        this(FAQCategory.class, metadata, inits);
    }

    public QFAQCategory(Class<? extends FAQCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QFAQCategory(forProperty("parent"), inits.get("parent")) : null;
    }

}

