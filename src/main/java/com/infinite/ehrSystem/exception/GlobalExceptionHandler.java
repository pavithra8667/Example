package com.infinite.ehrSystem.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LabOrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLabOrderNotFound(
            LabOrderNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(LabResultNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLabResultNotFound(
            LabResultNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(DuplicateLabResultException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateLabResult(
            DuplicateLabResultException ex) {

        return buildResponse(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidLabOrderStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLabOrderState(
            InvalidLabOrderStateException ex) {

        return buildResponse(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidResultValueException.class)
    public ResponseEntity<ErrorResponse> handleInvalidResultValue(
            InvalidResultValueException ex) {

        return buildResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                ex.getMessage()
        );
    }

    @ExceptionHandler(UnsupportedResultTypeException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedResultType(
            UnsupportedResultTypeException ex) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    @ExceptionHandler(VisitNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVisitNotFound(
            VisitNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(VisitAlreadyClosedException.class)
    public ResponseEntity<ErrorResponse> handleVisitAlreadyClosed(
            VisitAlreadyClosedException ex) {

        return buildResponse(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
    }



    @ExceptionHandler(PrescriptionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePrescriptionNotFound(
            PrescriptionNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(EmptyPrescriptionException.class)
    public ResponseEntity<ErrorResponse> handleEmptyPrescription(
            EmptyPrescriptionException ex) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    @ExceptionHandler(DiagnosisNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDiagnosisNotFound(
            DiagnosisNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                message
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(
            HttpMessageNotReadableException ex) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid date format or request body"
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex) {

        return buildResponse(
                HttpStatus.FORBIDDEN,
                "Access Denied"
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()
        );
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String message) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        LocalDateTime.now(),
                        status.value(),
                        status.name(),
                        message
                );

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }
}
