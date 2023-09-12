package com.spharos.ssgpoint.attendancecheck.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendanceCheck is a Querydsl query type for AttendanceCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendanceCheck extends EntityPathBase<AttendanceCheck> {

    private static final long serialVersionUID = 570556846L;

    public static final QAttendanceCheck attendanceCheck = new QAttendanceCheck("attendanceCheck");

    public final com.spharos.ssgpoint.global.QBaseEntity _super = new com.spharos.ssgpoint.global.QBaseEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final StringPath UUID = createString("UUID");

    public QAttendanceCheck(String variable) {
        super(AttendanceCheck.class, forVariable(variable));
    }

    public QAttendanceCheck(Path<? extends AttendanceCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendanceCheck(PathMetadata metadata) {
        super(AttendanceCheck.class, metadata);
    }

}

