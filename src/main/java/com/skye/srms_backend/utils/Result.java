package com.skye.srms_backend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static<T> Result<T> success(){
        return new Result<>(2000, "successful", null);
    }

    public static<T> Result<T> success(T data){
        return new Result<>(20000, "successful", data);
    }

    public static<T> Result<T> success(T data, String message){
        return new Result<>(20000, message, data);
    }

    public static<T> Result<T> success(String message){
        return new Result<>(20000, message, null);
    }


    public static<T> Result<T> fail(){
        return new Result<>(20001, "failed", null);
    }

    public static<T> Result<T> fail(Integer code){
        return new Result<>(code, "failed", null);
    }

    public static<T> Result<T> fail(Integer code, String message){
        return new Result<>(code, message, null);
    }

    public static<T> Result<T> fail(String message){
        return new Result<>(20001, message, null);
    }




}
