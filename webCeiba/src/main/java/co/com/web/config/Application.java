package co.com.web.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"co.com.persistencia.crud"
})

@ComponentScan(basePackages = {
		"co.com.web.config",
		"co.com.web.controller",
		"co.com.web.handler",
		"co.com.persistenciaimplementacion",
		"co.com.persistencia.entity",
		"factory"
		
})
@ImportResource("classpath:spring.xml")		 
@EntityScan(basePackages  = {"co.com.persistencia.entity"})
public class Application {
	
	 private static final Logger LOGGER = LogManager.getLogger(Application.class);
	  public static void main(String[] args) {
		  try {
	    		SpringApplication.run(Application.class, args);	        	
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			} 	
	    }
}
