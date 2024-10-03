package com.example.la.producto.controller;

import com.example.la.producto.document.Catalogo;
import com.example.la.producto.domain.ImagenRequest;
import com.example.la.producto.service.CatalogoService;
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
public class CatalogoControllerTest {

    @InjectMocks
    private CatalogoController catalogoController;

    @Mock
    private CatalogoService catalogoService;

    @Test
    public void getServiceTest(){
        GenericServ<Catalogo> service = catalogoController.getService();
        Assert.notNull(service,"OK");
    }

    @Test
    public void actualizaImagenCatalogoTest(){
        Catalogo catalogo=new Catalogo();
        Mono<Catalogo> monoCatalogo=Mono.just(catalogo);
        Mockito.when(catalogoService.actualizaImagenCatalogo(anyString(),anyString())).thenReturn(monoCatalogo);
        ImagenRequest imagenRequest=new ImagenRequest();
        imagenRequest.setImg("img");
        Mono<Catalogo> respuesta = catalogoController.actualizaImagenCatalogo("id", imagenRequest);

        respuesta.subscribe(cat-> Assert.notNull(cat,"OK"));

    }
}
