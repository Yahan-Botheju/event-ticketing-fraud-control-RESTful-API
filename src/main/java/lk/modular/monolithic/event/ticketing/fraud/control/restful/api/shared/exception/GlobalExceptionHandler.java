package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.DTOs.ApiResponse;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.DTOs.ErrorDetails;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //initiate response builder method
    private ResponseEntity<ApiResponse<Void>> buildResponse(
            HttpStatus status,
            String message,
            WebRequest request
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                status.value(),
                message,
                request.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(ApiResponse.error(errorDetails), status);
    }

    //400 - Bad Request
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequestException(
            BadRequestException ex,
            WebRequest request
    ){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    //400 DTO validation Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            WebRequest request
    ){
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildResponse(HttpStatus.BAD_REQUEST, errorMessage, request);
    }

    //401 Unauthorized
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnauthorized(
         UnauthorizedException ex,
         WebRequest request
    ){
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    //403 Forbidden
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<Void>> handleForbidden(
            ForbiddenException ex,
            WebRequest request
    ){
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage(), request);
    }

    //404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request
    ){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    //409 conflict
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Void>> handleConflict(
            ConflictException ex,
            WebRequest request
    ){
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    //500 Internal server error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception ex,
            WebRequest request
    ){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected internal server error", request);
    }
}
