package com.spharos.ssgpoint.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(length = 500)
    private String content;

    @Column(nullable = false)
    private String thumbnailUrl;

    private String bannerUrl;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "event_types", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "event_type")
    @Builder.Default
    private Set<EventType> eventTypes = new HashSet<>();

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("event")
    private Set<EventImage> eventImages;
}
