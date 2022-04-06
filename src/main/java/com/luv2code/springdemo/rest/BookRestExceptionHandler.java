package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookRestExceptionHandler {

	//Add excption handling code
		// Add an Exception handler using @ExceptionHandler

		@ExceptionHandler
		public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException exc) {

			// create a StudentErrorResponse
			BookErrorResponse error = new BookErrorResponse();

			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());

			// return ResponseEntity

			return new ResponseEntity<BookErrorResponse>(error, HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler
		public ResponseEntity<BookErrorResponse> handleException(Exception exc) {

			BookErrorResponse error = new BookErrorResponse();

			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
}
