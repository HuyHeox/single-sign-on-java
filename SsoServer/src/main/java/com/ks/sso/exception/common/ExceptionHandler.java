package com.ks.sso.exception.common;

import com.ks.sso.response.ErrorResponse;
import com.ks.sso.response.Response;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({ BusinessException.class })
    public ResponseEntity<Response> applicationErrorHandler(BusinessException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//        log.warn("Business Exception", e);
        String errMsg = e.getErr().getMessageKey();

        return new ResponseEntity<>(ErrorResponse.of(e.getErr().getMessageKey(), errMsg, e.getParams()),badRequest);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ NoHandlerFoundException.class })
    public String applicationErrorHandler(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//        log.warn("Business Exception", e);
//        String errMsg = e.getErr().getMessageKey();
//        return new ResponseEntity<>(ErrorResponse.of(e.getErr().getMessageKey(), errMsg, e.getParams()),badRequest);
        return "404NotFound";
    }
}
