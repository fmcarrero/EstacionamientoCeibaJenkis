package co.com.testweb;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.*;

import org.junit.Test;
import co.com.web.handler.ApiError;

public class ApiErrorTest {
	
	@Test
	public void ApiErrorOk(){
		//arrange		
		//act
		ApiError apiError= new ApiError();
		//assert
		assertNotNull(apiError.timestamp);
	}
	
	@Test
	public void getStatusOk(){
		//arrage
		ApiError apiError= new ApiError(HttpStatus.BAD_REQUEST);
		//act
		HttpStatus httpStatus = apiError.getStatus();
		//assert
		assertEquals(httpStatus,HttpStatus.BAD_REQUEST);
	}
	@Test
	public void ApiError2Ok(){
		//arrange		
		//act
		ApiError apiError= new ApiError(HttpStatus.BAD_REQUEST, new Exception("error"));
		//assert
		assertEquals(apiError.getMessage(), "Unexpected error");
	}
	@Test
	public void ApiError3Ok(){
		//arrange		
		//act
		ApiError apiError= new ApiError(HttpStatus.BAD_REQUEST, "error",new Exception("error"));
		//assert
		assertEquals(apiError.getMessage(), "error");
	}
	
}
