package com.example.la.producto.service.impl;

import com.example.la.producto.document.Catalogo;
import com.example.la.producto.repository.CatalogoRepository;
import com.example.la.producto.repository.GenericRepo;
import com.example.la.producto.service.CatalogoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CatalogoServiceImplTest {

    @InjectMocks
    private CatalogoServiceImpl catalogoService;

    @Mock
    private CatalogoRepository repository;

    @Test
    public void getRepository(){
        GenericRepo<Catalogo> repo = catalogoService.getRepository();
        Assert.notNull(repo,"OK");
    }

    @Test
    public void actualizaImagenCatalogoTest(){
        Catalogo catalogo=new Catalogo();
        catalogo.setUrl("url");
        catalogo.setId("id");
        catalogo.setName("name");
        catalogo.setCreateAt(new Date());
        Mono<Catalogo> catalogoMono=Mono.just(catalogo);
        Mockito.when(repository.findById(anyString())).thenReturn(catalogoMono);
        Mockito.when(repository.save(any(Catalogo.class))).thenReturn(catalogoMono);
        Mono<Catalogo> respuesta = catalogoService.actualizaImagenCatalogo("id", "url");

        respuesta.subscribe(cat->{
            Assert.notNull(cat.getUrl(),"OK");
            Assert.notNull(cat.getId(),"OK");
            Assert.notNull(cat.getName(),"OK");
            Assert.notNull(cat.getCreateAt(),"OK");
        });
    }
}
