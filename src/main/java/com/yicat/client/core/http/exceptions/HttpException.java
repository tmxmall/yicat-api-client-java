package com.yicat.client.core.http.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpException extends YicatApiException {

    public Error error;

    @Data
    public static class Error {

        public String code;
        public String message;

    }

    public static HttpException fromMessage(String message) {
        var resp = new HttpException();
        var error = new Error();
        error.setMessage(message);
        resp.setError(error);
        return resp;
    }
}
