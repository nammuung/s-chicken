package com.groups.schicken.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResultVO<T> {
    private HttpStatus status;
    private String message;
    private T data;
    public ResultVO(final HttpStatus statusCode, final String resultMsg) {
        this.status = statusCode;
        this.message = resultMsg;
        this.data = null;
    }

    public static<T> ResultVO<T> res(final HttpStatus statusCode, final String resultMsg) {
        return res(statusCode, resultMsg, null);
    }

    public static<T> ResultVO<T> res(final HttpStatus statusCode, final String resultMsg, final T t) {
        return ResultVO.<T>builder()
                .data(t)
                .status(statusCode)
                .message(resultMsg)
                .build();
    }
}
