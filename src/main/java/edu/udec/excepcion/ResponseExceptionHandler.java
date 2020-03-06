package edu.udec.excepcion;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * Exepción genérica
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorWrapper> manejadorException(Exception ex,
			WebRequest request) {
		
			 ErrorWrapper er = new ErrorWrapper(500, "Error interno en el servidor", ex.getMessage(), ex.fillInStackTrace().toString(), request.getDescription(false));
		   return new ResponseEntity<ErrorWrapper>(er, HttpStatus.INTERNAL_SERVER_ERROR);					
	}
	
	/**
	 * Exepcion not found
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NotFoundModelException.class)
	public final ResponseEntity<ErrorWrapper> manejadorModelException(NotFoundModelException ex,
			WebRequest request) {
			 ErrorWrapper er = new ErrorWrapper(404, "Not Found", ex.getMessage(), ex.fillInStackTrace().toString(), request.getDescription(false));
		   return new ResponseEntity<ErrorWrapper>(er, HttpStatus.NOT_FOUND);					
	}

	/*
	 * Error 
	 */
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorWrapper> manejadorNullPointerException(NullPointerException ex,
			WebRequest request) {
			 ErrorWrapper er = new ErrorWrapper(500, "Error interno en el servidor", ex.getMessage(), ex.fillInStackTrace().toString(), request.getDescription(false));
		   return new ResponseEntity<ErrorWrapper>(er, HttpStatus.INTERNAL_SERVER_ERROR);					
	}
	
	/*
	 * Excepcion no encontrado
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
			ErrorWrapper er = new ErrorWrapper(404, "No encontrado", ex.getMessage(), ex.fillInStackTrace().toString(), request.getDescription(false));
			return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Error para argumeto no válidos
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(400, "Bad Request", ex.getMessage(),ex.getMessage() , request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Filtro del error cuando el metodo no es el correcto 
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(405, "Method Not Allowed", ex.getMessage(), ex.fillInStackTrace().toString(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/*
	 * filtro error cuando el arametro entrada no es correcto
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(400, "Bad Request", ex.getMessage(), ex.fillInStackTrace().toString(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);		
	}	

}
