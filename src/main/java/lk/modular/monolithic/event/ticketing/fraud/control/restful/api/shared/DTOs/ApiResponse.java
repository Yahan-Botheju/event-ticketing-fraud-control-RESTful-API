package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.DTOs;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final ErrorDetails errorDetails;

    public ApiResponse(boolean success, T data, ErrorDetails errorDetails) {
        this.success = success;
        this.data = data;
        this.errorDetails = errorDetails;
    }

}
