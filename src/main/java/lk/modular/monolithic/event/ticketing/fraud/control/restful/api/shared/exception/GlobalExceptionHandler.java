package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.DTOs.ApiResponse;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.DTOs.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

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
}
