package com.spharos.ssgpoint.term.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserServiceTerm is a Querydsl query type for UserServiceTerm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserServiceTerm extends EntityPathBase<UserServiceTerm> {

    private static final long serialVersionUID = -1251112860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserServiceTerm userServiceTerm = new QUserServiceTerm("userServiceTerm");

    public final com.spharos.ssgpoint.global.QBaseEntity _super = new com.spharos.ssgpoint.global.QBaseEntity(this);

    public final BooleanPath agreed = createBoolean("agreed");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.spharos.ssgpoint.user.domain.QUser user;

    public QUserServiceTerm(String variable) {
        this(UserServiceTerm.class, forVariable(variable), INITS);
    }

    public QUserServiceTerm(Path<? extends UserServiceTerm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserServiceTerm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserServiceTerm(PathMetadata metadata, PathInits inits) {
        this(UserServiceTerm.class, metadata, inits);
    }

    public QUserServiceTerm(Class<? extends UserServiceTerm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.spharos.ssgpoint.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

