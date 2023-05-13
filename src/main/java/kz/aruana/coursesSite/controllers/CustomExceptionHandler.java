package kz.aruana.coursesSite.controllers;

import kz.aruana.coursesSite.dto.request.exceptiondto.ExceptionResponse;
import kz.aruana.coursesSite.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Validated
@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(generateException(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<ExceptionResponse> handleAlreadyExists(AlreadyExists ex){
        return new ResponseEntity<>(generateException(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RecordNotCreatedException.class)
    public ResponseEntity<ExceptionResponse> handleRecordNotCreated(RecordNotCreatedException ex){
        return new ResponseEntity<>(generateException(ex.getMessage()), HttpStatus.CREATED);
    }
    @ExceptionHandler(InvalidAuthException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidAuthException(InvalidAuthException ex){
        return new ResponseEntity<>(generateException(ex.getMessage()), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<ExceptionResponse> handleDeleteException(DeleteException ex) {
        return new ResponseEntity<>(generateException(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    private ExceptionResponse generateException(String message){
        ExceptionResponse errorResponse = new ExceptionResponse(message);
        return errorResponse;
    }

}
