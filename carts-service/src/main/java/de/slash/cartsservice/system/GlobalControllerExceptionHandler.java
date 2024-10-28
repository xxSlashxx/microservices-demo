package de.slash.cartsservice.system;

import de.slash.cartsservice.cart.CartNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@ControllerAdvice
class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCartNotFoundException(CartNotFoundException e) {
        ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, e.getLocalizedMessage());
        return ResponseEntity.status(NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ProblemDetail problemDetail = forStatusAndDetail(CONFLICT, e.getLocalizedMessage());
        return ResponseEntity.status(CONFLICT).body(problemDetail);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ProblemDetail> handleWebClientResponseException(WebClientResponseException e) {
        ProblemDetail problemDetail = forStatusAndDetail(e.getStatusCode(), e.getLocalizedMessage());
        return ResponseEntity.status(e.getStatusCode()).body(problemDetail);
    }
}
