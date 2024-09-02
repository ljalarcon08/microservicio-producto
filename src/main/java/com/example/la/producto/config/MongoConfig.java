package com.example.la.producto.config;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import jakarta.annotation.PostConstruct;

@Configuration
public class MongoConfig {

	  @Autowired
	  private MappingMongoConverter mongoConverter;
	  
	  @PostConstruct
	  public void setUpMongoEscapeCharacterAndTypeMapperConversion() {
	      mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
	  }
	
}
