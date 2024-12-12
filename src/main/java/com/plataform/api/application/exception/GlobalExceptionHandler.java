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
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler  {
    // Manejo de ResponseStatusException
    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<CustomException>> handleResponseStatusException(CustomException ex) {
        return Mono.just(
                ResponseEntity
                        .status(ex.getStatus())
                        .body(ex));
    }

    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<CustomException>> handleGenericException(Exception ex) {
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new CustomException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Ocurrió un error inesperado."
                        ))
        );
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
