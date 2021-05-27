package com.paszynski.empik.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class RequestCounter implements Serializable {

  @Id
  @Column(name = "LOGIN")
  private String login;

  @Column(name = "REQUEST_COUNT")
  private Long requestCount;
}
