package com.example.la.producto.service.impl;

import com.example.la.producto.document.Producto;
import com.example.la.producto.domain.PaginaProducto;
import com.example.la.producto.repository.GenericRepo;
import com.example.la.producto.repository.ProductoRepository;
import com.example.la.producto.service.ProductoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductoServiceImplTest{

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Mock
    private ProductoRepository repository;

    private Flux<Producto> productos;
    private Mono<Producto> producto;
    Pageable pageable;

    @BeforeAll
    public void init() {
        Producto prod=new Producto();
        prod.setId("id");
        prod.setImg("img");
        prod.setCantidad(1L);
        prod.setMarca("marca");
        prod.setCreateAt(new Date());
        prod.setPrize(1L);
        prod.setName("name");
        prod.setIdCatalogo("idcat");
        productos=Flux.just(prod,prod);
        producto=Mono.just(prod);
        pageable= PageRequest.of(0,10);
    }

    @Test
    public void getRepositoryTest(){
        GenericRepo<Producto> repo = productoService.getRepository();
        Assert.notNull(repo,"OK");
    }

    @Test
    public void findProductoByCatalogoTest(){
        Mockito.when(repository.findProductoByCatalogo(anyString(),any(Pageable.class))).thenReturn(productos);

        Flux<Producto> respuesta = productoService.findProductoByCatalogo("catalogo", pageable);

        respuesta.subscribe(prod->Assert.notNull(prod,"OK"));
    }

    @Test
    public void getPaginaProductoTest(){
        Mono<Long> monoLong=Mono.just(2L);
        Mockito.when(repository.findAllBy(any(Pageable.class))).thenReturn(productos);
        Mockito.when(repository.count()).thenReturn(monoLong);
        Mono<PaginaProducto> respuesta = productoService.getPaginaProducto(pageable);
        respuesta.subscribe(prod->{
            Assert.notNull(prod,"OK");
            Assert.notNull(prod.getProductos(),"OK");
            Assert.notNull(prod.getTotalRegistros(),"OK");
            prod.setProductos(new ArrayList<>());
            prod.setTotalRegistros(0L);
        });

    }

    @Test
    public void findProductoByNameTest(){
        Mockito.when(repository.encuentraProductoPorNombre(anyString())).thenReturn(productos);
        Flux<Producto> respuesta = productoService.findProductoByName("name");
        respuesta.subscribe(prod->Assert.notNull(prod,"OK"));
    }

    @Test
    public void getElementsTest(){
        Mockito.when(repository.findAll()).thenReturn(productos);
        Flux<Producto> respuesta = productoService.getElements();
        respuesta.subscribe(prod->Assert.notNull(prod,"OK"));
    }

    @Test
    public void getElementByIdTest(){
        Mockito.when(repository.findById(anyString())).thenReturn(producto);
        Mono<Producto> respuesta = productoService.getElementById("id");
        respuesta.subscribe(prod->{
            Assert.notNull(prod,"OK");
            Assert.notNull(prod.getId(),"OK");
            Assert.notNull(prod.getImg(),"OK");
            Assert.notNull(prod.getCantidad(),"OK");
            Assert.notNull(prod.getMarca(),"OK");
            Assert.notNull(prod.getCreateAt(),"OK");
            Assert.notNull(prod.getPrize(),"OK");
            Assert.notNull(prod.getName(),"OK");
            Assert.notNull(prod.getIdCatalogo(),"OK");
        });
    }

    @Test
    public void crearElementTest(){
        Producto producto1=new Producto();
        Mockito.when(repository.save(any(Producto.class))).thenReturn(producto);
        Mono<Producto> respuesta = productoService.crearElement(producto1);
        respuesta.subscribe(prod->Assert.notNull(prod,"OK"));
    }

    @Test
    public void actualizarElementTest(){
        Producto producto1=new Producto();
        Mockito.when(repository.save(any(Producto.class))).thenReturn(producto);
        Mono<Producto> respuesta = productoService.actualizarElement(producto1);
        respuesta.subscribe(prod->Assert.notNull(prod,"OK"));
    }

    @Test
    public void actualizaImagenProductoTest(){
        Mockito.when(repository.findById(anyString())).thenReturn(producto);
        Mockito.when(repository.save(any(Producto.class))).thenReturn(producto);
        Mono<Producto> respuesta = productoService.actualizaImagenProducto("id", "img");
        respuesta.subscribe(prod->Assert.notNull(prod,"OK"));
    }

    @Test
    public void eliminaElementTest(){
        Mockito.when(repository.deleteById(anyString())).thenReturn(Mono.empty());
        productoService.eliminaElement("id");
    }
}
