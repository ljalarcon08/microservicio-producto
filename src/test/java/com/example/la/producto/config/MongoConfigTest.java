package com.example.la.producto.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MongoConfigTest {

    @InjectMocks
    private MongoConfig mongoConfig;

    @Mock
    private MappingMongoConverter mongoConverter;

    @Test
    public void setUpMongoEscapeCharacterAndTypeMapperConversionTest(){
        Mockito.doNothing().when(mongoConverter).setTypeMapper(any(DefaultMongoTypeMapper.class));
        mongoConfig.setUpMongoEscapeCharacterAndTypeMapperConversion();
    }
}
