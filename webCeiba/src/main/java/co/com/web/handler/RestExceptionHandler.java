package co.com.web.handler;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;
import org.springframework.core.Ordered;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {
	 @Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	       String error = "Malformed JSON request";
	       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	   }

	   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	   
	   @ExceptionHandler(org.hibernate.ObjectNotFoundException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(org.hibernate.ObjectNotFoundException ex) {
	       ApiError apiError = new ApiError(NOT_FOUND,"objecto no encontrado",ex);
	        return buildResponseEntity(apiError);
	   }
	   @ExceptionHandler(DataIntegrityViolationException.class)
	   protected ResponseEntity<Object> handleEntityVehiculoException(DataIntegrityViolationException ex) {
	       ApiError apiError = new ApiError(CONFLICT,ex.getMessage(),ex);
	       return buildResponseEntity(apiError);
	   }
}
