package com.spharos.ssgpoint.associatepointcard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociatePointCardCreateDto {

    private String number;
    private String UUID;
    private String type;

}
