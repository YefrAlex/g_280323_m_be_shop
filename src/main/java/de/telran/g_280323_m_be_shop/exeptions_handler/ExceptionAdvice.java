package de.telran.g_280323_m_be_shop.exeptions_handler;

import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.EntityValidationException;
import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.ThirdTestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ThirdTestException.class)
    public ResponseEntity<Response> handleException (ThirdTestException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler (EntityValidationException.class)
    public ResponseEntity<Response> handleException (EntityValidationException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
