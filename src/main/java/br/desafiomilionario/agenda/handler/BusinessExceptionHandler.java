package br.desafiomilionario.agenda.handler;

import br.desafiomilionario.agenda.exception.BusinessException;
import br.desafiomilionario.agenda.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ErrorDto handler(BusinessException ex) {
        return new ErrorDto(
                ex.getMessage()
        );
    }
}
