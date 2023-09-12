package com.spharos.ssgpoint.event.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventImageList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_image_id")
    private EventImage eventImage;

}
