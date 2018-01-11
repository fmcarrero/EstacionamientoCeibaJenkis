package co.com.web.handler;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {
	   private String message;
	   private String debugMessage;
	   private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   public LocalDateTime timestamp;
	   
	  
	   public HttpStatus getStatus() {return this.status;}

	   public ApiError() {
		   timestamp = LocalDateTime.now();
		   
	   }
	  
	   public String getMessage() { return this.message;}
	   public String getDebugMessage() { return this.debugMessage;}
	   
	  public ApiError(HttpStatus status) {
	       this();
	       this.status = status;
	    }

	   public ApiError(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = "Unexpected error";
	       this.debugMessage = ex.getLocalizedMessage();
	   }
	  
	   public ApiError(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }
	}

