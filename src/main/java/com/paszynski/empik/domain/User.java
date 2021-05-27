package com.paszynski.empik.domain;

import lombok.*;

import java.math.BigDecimal;

@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public long id;
    public String login;
    public String name;
    public String type;
    public String avatarUrl;
    public String createdAt;
    public BigDecimal calculations;

}
