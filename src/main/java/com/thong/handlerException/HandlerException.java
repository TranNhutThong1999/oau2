package com.thong.handlerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class HandlerException {
	

    @ExceptionHandler(value =NoHandlerFoundException.class)
    @ResponseBody
    public ResponseStatusException handleError404(NoHandlerFoundException  e )   {
        return  new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
    }
}
