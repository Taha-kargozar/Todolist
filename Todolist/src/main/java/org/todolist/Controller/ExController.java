package org.todolist.Controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.todolist.Execption.NotFoundException;
import org.todolist.dto.ErrorDTO;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExController {
    private final MessageSourceAccessor messageSourceAccessor;

    public ExController(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex) {
        ErrorDTO errorDto = new ErrorDTO(ex.getMessage(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors();
        List<ErrorDTO> errorDtos = new ArrayList<>();
        for (int i = 0; i < fieldErrors.size(); i++) {
            ErrorDTO errorDto = new ErrorDTO(messageSourceAccessor.getMessage(fieldErrors.get(i).getDefaultMessage())
                    , fieldErrors.get(i).getCode());
            errorDtos.add(errorDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDtos);
    }
}
