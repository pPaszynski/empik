package com.paszynski.empik.exception;

import lombok.*;

@Data
@AllArgsConstructor
class ErrorDetails {

  private Integer status;
  private String message;
}
