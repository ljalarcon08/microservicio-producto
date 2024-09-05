package com.example.la.producto.controller;

import com.example.la.producto.document.Producto;
import com.example.la.producto.domain.ImagenRequest;
import com.example.la.producto.domain.PaginaProducto;
import com.example.la.producto.service.GenericServ;
import com.example.la.producto.service.ProductoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductoControllerTest {


    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    private Flux<Producto> productos;
    private Mono<Producto> producto;

    @BeforeAll
    public void init() {
        Producto prod=new Producto();
        productos=Flux.just(prod,prod);
        producto=Mono.just(prod);
    }

    @Test
    public void getServiceTest(){
        GenericServ<Producto> service = productoController.getService();
        Assert.notNull(service,"OK");
    }

    @Test
    public void getProductoByCatalogoTest(){

        Mockito.when(productoService.findProductoByCatalogo(anyString(),any(Pageable.class))).thenReturn(productos);

        Flux<Producto> respuesta = productoController.getProductoByCatalogo("id", 0, 5);

        Assert.notNull(respuesta,"OK");
    }

    @Test
    public void getProductoByPaginaTest(){
        Producto producto=new Producto();
        List<Producto> productos=new ArrayList<>();
        productos.add(producto);
        productos.add(producto);
        PaginaProducto paginaProducto=new PaginaProducto(productos,(long)productos.size());
        Mono<PaginaProducto> monoPagina=Mono.just(paginaProducto);
        Mockito.when(productoService.getPaginaProducto(any(Pageable.class))).thenReturn(monoPagina);
        Mono<PaginaProducto> respuesta = productoController.getProductoByPagina(0, 5);

        PaginaProducto respuestaPag=respuesta.block();

        Assert.state(respuestaPag!=null && respuestaPag.getTotalRegistros()!=null && respuestaPag.getTotalRegistros()==2,"OK");
    }

    @Test
    public void getProductosByNameTest(){
        Mockito.when(productoService.findProductoByName(anyString())).thenReturn(productos);
        Flux<Producto> respuesta = productoController.getProductosByName("name");

        respuesta.subscribe(producto->Assert.notNull(producto,"OK"));
    }

    @Test
    public void actualizaImagenProducto() {
        ImagenRequest imagenRequest=new ImagenRequest();
        imagenRequest.setImg("img");
        Mockito.when(productoService.actualizaImagenProducto(anyString(),anyString())).thenReturn(producto);
        Mono<Producto> respuesta = productoController.actualizaImagenProducto("id", imagenRequest);

        respuesta.subscribe(producto->Assert.notNull(producto,"OK"));
    }

    @Test
    public void getAllElementsTest(){

        Mockito.when(productoService.getElements()).thenReturn(productos);
        Flux<Producto> respuesta = productoController.getAllElements();

        respuesta.subscribe(producto->Assert.notNull(producto,"OK"));
    }


    @Test
    public void getElementByIdTest(){
        Mockito.when(productoService.getElementById(anyString())).thenReturn(producto);
        Mono<Producto> respuesta = productoController.getElementById("id");

        respuesta.subscribe(producto-> Assert.notNull(producto,"OK"));
    }

    @Test
    public void crearElementTest(){
        Mockito.when(productoService.crearElement(any(Producto.class))).thenReturn(producto);
        Producto productoCrear=new Producto();
        Mono<Producto> respuesta = productoController.crearElement(productoCrear);

        respuesta.subscribe(producto-> Assert.notNull(producto,"OK"));
    }

    @Test
    public void actualizarElementTest(){
        Mockito.when(productoService.actualizarElement(any(Producto.class))).thenReturn(producto);
        Producto productoActualizar=new Producto();
        Mono<Producto> respuesta = productoController.actualizarElement("id",productoActualizar);

        respuesta.subscribe(producto-> Assert.notNull(producto,"OK"));
    }

    @Test
    public void eliminarElementTest(){
        Mockito.when(productoService.eliminaElement(anyString())).thenReturn(Mono.empty());
        productoController.eliminarElement("id");
    }
}
