package com.levimartines.springkotlin.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handleNotFound(ex: NoSuchElementException) = ResponseEntity(ex.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler
    fun handleBadRequest(ex: IllegalArgumentException) = ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
}