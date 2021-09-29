package com.example.work.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessResponse implements Serializable {

    public static final long serialVersionUID = 5926468583005150707L;
    public String statusCode;
    public String statusMessage;

}


