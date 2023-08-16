package com.spharos.ssgpoint.event.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String thumbnail; //todo:s3확인해야됨

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer type;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;
}
