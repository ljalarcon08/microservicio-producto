package com.example.la.producto.controller;

import com.example.la.producto.document.Carro;
import com.example.la.producto.service.CarroService;
import com.example.la.producto.service.GenericServ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CarroControllerTest {

    @InjectMocks
    private CarroController carroController;

    @Mock
    private CarroService carroService;

    @Test
    public void getServiceTest(){
        GenericServ<Carro> service = carroController.getService();
        Assert.notNull(service,"OK");
    }

    @Test
    public void getCarroByEmailTest(){
        Mono<Carro> monoCarro=Mono.just(new Carro());
        Mockito.when(carroService.getCarroByEmail(anyString())).thenReturn(monoCarro);
        Mono<Carro> respuesta = carroController.getCarroByEmail("email");
        respuesta.subscribe(carro->Assert.notNull(carro,"OK"));
    }
}
