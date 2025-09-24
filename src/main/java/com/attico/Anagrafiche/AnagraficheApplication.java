package com.attico.Anagrafiche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
classe principale di avvio di spring boot
 */
@SpringBootApplication

//abilita Springfox Swagger 2 - permette di generare documentazione API
@EnableSwagger2
public class AnagraficheApplication {

	/*
	configurazione Swagger, Docket è classe di SpringFox configurata per:
	-includere solo le classi annotate come restController nella doc
	-includere tutti i percorsi dei controller selezionati
	- disabilitare i messaggi di risposta predefiniti
	 */
	@Bean //crea l'oggetto e lo gestisce come bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}

	@Bean
	public ApiInfo apiInfo() {
		final ApiInfoBuilder builder = new ApiInfoBuilder();
		return builder.build();
	}

	//avvia applicazione Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(AnagraficheApplication.class, args);
	}

}
