package com.spharos.ssgpoint.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = -1653312590L;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final StringPath barcode = createString("barcode");

    public final StringPath content = createString("content");

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isUsed = createBoolean("isUsed");

    public final StringPath number = createString("number");
  
    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);


    public final StringPath store = createString("store");

    public final StringPath title = createString("title");

    public QCoupon(String variable) {
        super(Coupon.class, forVariable(variable));
    }

    public QCoupon(Path<? extends Coupon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoupon(PathMetadata metadata) {
        super(Coupon.class, metadata);
    }

}

