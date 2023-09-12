package com.spharos.ssgpoint.faq.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.core.types.dsl.PathInits;



/**
 * QFAQ is a Querydsl query type for FAQ
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFAQ extends EntityPathBase<FAQ> {

    private static final long serialVersionUID = 1064255214L;
    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFAQ fAQ = new QFAQ("fAQ");

   public final StringPath answer = createString("answer");

    public final QFAQCategory category;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath question = createString("question");

    public QFAQ(String variable) {
        this(FAQ.class, forVariable(variable), INITS);
    }

    public QFAQ(Path<? extends FAQ> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFAQ(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFAQ(PathMetadata metadata, PathInits inits) {
        this(FAQ.class, metadata, inits);
    }

    public QFAQ(Class<? extends FAQ> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QFAQCategory(forProperty("category"), inits.get("category")) : null;
    }

}

