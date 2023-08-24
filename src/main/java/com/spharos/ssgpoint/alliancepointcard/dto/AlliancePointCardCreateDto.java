package com.spharos.ssgpoint.alliancepointcard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlliancePointCardCreateDto {

    private String number;
    private String UUID;
    private String type;

}
