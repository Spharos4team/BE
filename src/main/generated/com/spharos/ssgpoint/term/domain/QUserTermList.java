package com.spharos.ssgpoint.term.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTermList is a Querydsl query type for UserTermList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTermList extends EntityPathBase<UserTermList> {

    private static final long serialVersionUID = -760397209L;

    public static final QUserTermList userTermList = new QUserTermList("userTermList");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final MapPath<String, Boolean, BooleanPath> termJson = this.<String, Boolean, BooleanPath>createMap("termJson", String.class, Boolean.class, BooleanPath.class);

    public QUserTermList(String variable) {
        super(UserTermList.class, forVariable(variable));
    }

    public QUserTermList(Path<? extends UserTermList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTermList(PathMetadata metadata) {
        super(UserTermList.class, metadata);
    }

}

