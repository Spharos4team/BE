package com.spharos.ssgpoint.faq.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFAQ is a Querydsl query type for FAQ
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFAQ extends EntityPathBase<FAQ> {

    private static final long serialVersionUID = 1064255214L;

    public static final QFAQ fAQ = new QFAQ("fAQ");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QFAQ(String variable) {
        super(FAQ.class, forVariable(variable));
    }

    public QFAQ(Path<? extends FAQ> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFAQ(PathMetadata metadata) {
        super(FAQ.class, metadata);
    }

}

