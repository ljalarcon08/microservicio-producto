package com.example.la.producto.service.impl;

import com.example.la.producto.document.Carro;
import com.example.la.producto.document.Producto;
import com.example.la.producto.repository.CarroRepository;
import com.example.la.producto.repository.GenericRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CarroServiceImplTest {

    @InjectMocks
    private CarroServiceImpl carroService;

    @Mock
    private CarroRepository repository;

    @Test
    public void getRepository(){
        GenericRepo<Carro> repo = carroService.getRepository();
        Assert.notNull(repo,"OK");
    }

    @Test
    public void getCarroByEmailTest(){
        Carro carro=new Carro();
        carro.setEmail("email");
        carro.setId("id");
        List<Producto> productos=new ArrayList<>();
        Producto producto=new Producto();
        productos.add(producto);
        carro.setProductos(productos);
        carro.setCreateAt(new Date());
        Mono<Carro> monoCarro=Mono.just(carro);
        Mockito.when(repository.findByEmail(anyString())).thenReturn(monoCarro);
        Mono<Carro> respuesta = carroService.getCarroByEmail("email");
        respuesta.subscribe(carr->{
            Assert.notNull(carr.getEmail(),"OK");
            Assert.notNull(carr.getId(),"OK");
            Assert.notNull(carr.getCreateAt(),"OK");
            Assert.notNull(carr.getProductos(),"OK");
        });
    }
}
