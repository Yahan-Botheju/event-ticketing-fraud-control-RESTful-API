package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handler.DTOs;


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

    /* __HELPER_METHODS__ */


    //success response
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    //error response
    public static <T> ApiResponse<T> error(ErrorDetails errorDetails) {
        return new ApiResponse<>(false, null, errorDetails);
    }
}
