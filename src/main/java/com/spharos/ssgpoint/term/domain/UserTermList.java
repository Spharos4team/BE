package com.spharos.ssgpoint.term.domain;

import com.spharos.ssgpoint.user.domain.User;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTermList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(JsonType.class)
    @Column(columnDefinition = "longtext")
    private Map<String,Boolean> termJson;

    @OneToOne(mappedBy = "term",fetch = FetchType.LAZY)
    private User user;

    public void updateTermJson(Map<String, Boolean> termJson) {
        this.termJson = termJson;
    }
}
