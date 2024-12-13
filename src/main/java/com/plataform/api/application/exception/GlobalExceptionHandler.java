package com.plataform.api.application.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.MethodNotAllowedException;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler  {
    // Manejo de ResponseStatusException
    @ExceptionHandler(CustomException.class)
    public Mono<CustomException> handleResponseStatusException(CustomException ex) {
        return Mono.error(new CustomException(ex.getStatus(),ex.getMessage()));
    }
    @ExceptionHandler(NullPointerException.class)
    public Mono<CustomException> handleGenericException(NullPointerException ex) {
        return Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR,"Error interno del sistema."));

    }
    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public Mono<CustomException> handleGenericException(Exception ex) {
        return Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR,"Error interno del sistema."));

    }
    @ExceptionHandler(MethodNotAllowedException.class)
    public Mono<CustomException> handleMethodNotAllowed(MethodNotAllowedException ex) {
        return Mono.error(
                new CustomException(HttpStatus.METHOD_NOT_ALLOWED,"Método no permitido."));

    }


    /*public GlobalExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext, ServerCodecConfigurer codecConfigurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageReaders(codecConfigurer.getReaders());
        this.setMessageWriters(codecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(),this::customErrorResponse);
    }

    private Mono<ServerResponse> customErrorResponse(ServerRequest request) {
        Map<String, Object> errorMap = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        HttpStatus status = (HttpStatus) Optional.ofNullable(errorMap.get("status")).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        return ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(errorMap));
    }*/


}
