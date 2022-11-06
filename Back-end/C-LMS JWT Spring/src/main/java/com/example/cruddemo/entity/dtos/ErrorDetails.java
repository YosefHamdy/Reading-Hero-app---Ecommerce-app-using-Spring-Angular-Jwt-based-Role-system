package com.example.cruddemo.entity.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date timestamp;
    private String message;
    private String details;
    private Object[] paramObjects;

    public ErrorDetails(Date timestamp, String message, String details, Object[] paramObjects) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.paramObjects = paramObjects;
    }

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
