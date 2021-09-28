package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class BaseResponse<T> extends SuccessResponse implements Serializable {
    public static final long serialVersionUID = 5926468583005150707L;
    @Builder.Default
   public String StatusCode = "200";
    @Builder.Default
  public   String StatusMsg = "SUCCESS";
    private T Data;

}

