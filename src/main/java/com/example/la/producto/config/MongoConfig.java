package com.example.la.producto.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import jakarta.annotation.PostConstruct;

@Configuration
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
public class MongoConfig {

	  @Autowired
	  private MappingMongoConverter mongoConverter;
	  
	  @PostConstruct
	  public void setUpMongoEscapeCharacterAndTypeMapperConversion() {
	      mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
	  }
	
}
