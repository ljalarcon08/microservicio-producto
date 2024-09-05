package com.example.la.producto;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Generated
@EnableDiscoveryClient
@EnableReactiveMongoAuditing
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Producto", version = "1.0", description = "Producto"))
public class MicroservicioProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioProductoApplication.class, args);
	}

	
	@Bean
	public OpenAPI config() {
	    return new OpenAPI()
	            .addServersItem(serverInfo()).addServersItem(new Server().url("http://localhost:8090/api/producto"));
	}

	private Server serverInfo() {
	    return new Server()
	            .url("");
	}
}
