package com.masai.school.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.masai.school.payload.ApiResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobleExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse =new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> errRsp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String errMsg=error.getDefaultMessage();
			errRsp.put(fieldName, errMsg);
		});
		
		return new ResponseEntity<Map<String,String>>(errRsp,HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<Map<String,String>> constraintViolationException (ConstraintViolationException ex){
//		Map<String,String> errRsp=new HashMap<>();
//		 ex.getConstraintViolations().forEach((error)->{
//			String fieldName=((FieldError)error).getField();
//			String errMsg=error.getMessage();
//			errRsp.put(fieldName, errMsg);
//		});
//		
//		return new ResponseEntity<Map<String,String>>(errRsp,HttpStatus.BAD_REQUEST);
//	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
        
}
