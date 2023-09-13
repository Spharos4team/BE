package com.spharos.ssgpoint.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClubList is a Querydsl query type for ClubList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubList extends EntityPathBase<ClubList> {

    private static final long serialVersionUID = -325149168L;

    public static final QClubList clubList = new QClubList("clubList");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QClubList(String variable) {
        super(ClubList.class, forVariable(variable));
    }

    public QClubList(Path<? extends ClubList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClubList(PathMetadata metadata) {
        super(ClubList.class, metadata);
    }

}

