package com.grupo6.ConectaJob.ExceptionsConfig;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.returnErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class globalExceptionsHandler {
    @ExceptionHandler(notFound.class)
    public ResponseEntity<returnErrorDTO> handleReqBasicosCadastroNCompridos(notFound ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new returnErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<returnErrorDTO> handleReqBasicosCadastroNCompridos(DuplicateEntityException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new returnErrorDTO(ex.getMessage()));
    }
}