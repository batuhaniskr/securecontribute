package com.batuhan.youcontribute.exceptions;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorResponse implements Serializable {

  private String message;
}
