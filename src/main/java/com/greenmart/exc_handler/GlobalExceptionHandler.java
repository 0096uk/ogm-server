package com.greenmart.exc_handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.greenmart.dtos.ApiResponse;

@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RuntimeException.class) 
	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
		System.out.println("in global exc handler : run time exc");
		return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
	}
}
