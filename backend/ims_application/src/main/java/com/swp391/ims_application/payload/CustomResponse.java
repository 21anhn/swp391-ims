package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomResponse {
    private int status;
    private String message;
    private Object data;
    private boolean success;

}
