package com.spharos.ssgpoint.term.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QServiceTerm is a Querydsl query type for ServiceTerm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QServiceTerm extends EntityPathBase<ServiceTerm> {

    private static final long serialVersionUID = -1335824337L;

    public static final QServiceTerm serviceTerm = new QServiceTerm("serviceTerm");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath termContent = createString("termContent");

    public final StringPath termName = createString("termName");

    public QServiceTerm(String variable) {
        super(ServiceTerm.class, forVariable(variable));
    }

    public QServiceTerm(Path<? extends ServiceTerm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServiceTerm(PathMetadata metadata) {
        super(ServiceTerm.class, metadata);
    }

}

