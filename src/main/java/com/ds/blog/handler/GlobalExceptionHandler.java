package com.ds.blog.handler;

import com.ds.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(value=Exception.class)
  public ResponseDto<String> Exception(Exception e){
    return new ResponseDto<String>(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        e.getMessage());
  }
}
