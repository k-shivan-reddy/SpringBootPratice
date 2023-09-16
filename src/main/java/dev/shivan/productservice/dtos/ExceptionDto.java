package dev.shivan.productservice.dtos;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;
    public ExceptionDto(HttpStatus code,String message)
    {
        this.errorCode = code;
        this.message = message;
    }
}
