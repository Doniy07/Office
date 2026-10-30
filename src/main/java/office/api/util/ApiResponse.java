package office.api.util;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private String message;

    private Integer code;

    private Boolean isError;

    private T data;

    public ApiResponse(Integer code, Boolean isError) {
        this.code = code;
        this.isError = isError;
        this.data = null;
    }

    public ApiResponse(String message, Integer code, Boolean isError) {
        this.message = message;
        this.code = code;
        this.isError = isError;
        this.data = null;
    }

    public ApiResponse(Integer code, Boolean isError, T data, String message) {
        this.code = code;
        this.isError = isError;
        this.data = data;
        this.message = message;
    }


    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<T>(200, false, data, "Successful");
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<T>(200, false);
    }

    public static <T> ApiResponse<T> bad(String message) {
        return new ApiResponse<T>(message, 400, true);
    }

}
