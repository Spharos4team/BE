package com.spharos.ssgpoint.receipt.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReceipt is a Querydsl query type for Receipt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReceipt extends EntityPathBase<Receipt> {

    private static final long serialVersionUID = -1617659474L;

    public static final QReceipt receipt = new QReceipt("receipt");

    public final StringPath alliance = createString("alliance");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath brand = createString("brand");

    public final StringPath cardName = createString("cardName");

    public final StringPath cardNumber = createString("cardNumber");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath number = createString("number");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final StringPath pointCardNumber = createString("pointCardNumber");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath storeName = createString("storeName");

    public QReceipt(String variable) {
        super(Receipt.class, forVariable(variable));
    }

    public QReceipt(Path<? extends Receipt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReceipt(PathMetadata metadata) {
        super(Receipt.class, metadata);
    }

}

