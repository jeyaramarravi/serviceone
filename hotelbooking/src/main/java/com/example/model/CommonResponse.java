package com.example.model;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
	private String responseMessage;
	HttpStatus code;
}
