package pl.bak.pageRecognition;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.bak.pageRecognition.error.handler.RestErrorHandler;

@SpringBootApplication
public class PageRecognitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PageRecognitionApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplateBuilder()
				.errorHandler(new RestErrorHandler())
				.build();
	}

}
