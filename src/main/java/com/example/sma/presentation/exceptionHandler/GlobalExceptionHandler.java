package com.example.sma.presentation.exceptionHandler;

import com.example.sma.exception.EmptyValueException;
import com.example.sma.exception.InvalidNumberException;
import com.example.sma.exception.NotFoundEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundEmployeeException.class})
    @ResponseBody
    public Map<String, Object> notFountEmployeeExceptionHandler(NotFoundEmployeeException e) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.NOT_FOUND);
        errorMap.put("exception", e.getClass());
        errorMap.put("message", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmptyValueException.class})
    @ResponseBody
    public Map<String, Object> emptyValueExceptionHandler(EmptyValueException e) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.BAD_REQUEST);
        errorMap.put("exception", e.getClass());
        errorMap.put("message", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidNumberException.class})
    @ResponseBody
    public Map<String, Object> invalidNumberHandler(InvalidNumberException e) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.BAD_REQUEST);
        errorMap.put("exception", e.getClass());
        errorMap.put("message", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public Map<String, Object> validationExceptionHandler(ValidationException e) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.BAD_REQUEST);
        errorMap.put("exception", e.getClass());
        errorMap.put("message", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Map<String, Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.BAD_REQUEST);
        errorMap.put("exception", e.getClass());
        errorMap.put("message", "不正な登録内容です");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Map<String, Object> exceptionHandler() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "予期せぬエラーが発生しました");
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return errorMap;
    }

}
