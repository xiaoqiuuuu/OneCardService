package com.weiqt.app.result;

/**
 * 一个返回数据格式的类
 */

public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public Result(T data, boolean success, String message) {
        if (success) {
            this.code = 200;
            this.message = "success";
        } else {
            this.code = 500;
            this.message = message;
        }
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(500, message);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message);
    }
}