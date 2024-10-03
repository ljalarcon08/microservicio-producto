package com.example.la.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.la.producto.document.Producto;
import com.example.la.producto.domain.ImagenRequest;
import com.example.la.producto.domain.PaginaProducto;
import com.example.la.producto.service.GenericServ;
import com.example.la.producto.service.ProductoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/producto")
public class ProductoController extends GenericController<Producto>{

	@Autowired
	private ProductoService service;

	@Override
	public GenericServ<Producto> getService() {
		return service;
	}
	
	@GetMapping(path="/catalogo/{id}")
	public Flux<Producto> getProductoByCatalogo(@PathVariable(name="id") String idCatalogo,@RequestParam int pagina,@RequestParam int largo){
		Pageable pageable=PageRequest.of(pagina, largo);
		return service.findProductoByCatalogo(idCatalogo,pageable);
	}
	
	@GetMapping(path="/pagina")
	public Mono<PaginaProducto> getProductoByPagina(@RequestParam int pagina,@RequestParam int largo){
		Pageable pageable=PageRequest.of(pagina, largo);
		return service.getPaginaProducto(pageable);
	}
	
	@GetMapping(path="/nombre/{name}")
	public Flux<Producto> getProductosByName(@PathVariable String name){
		return service.findProductoByName(name);
	}
	
	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping(path="/imagen/{id}")
	public Mono<Producto> actualizaImagenProducto(@PathVariable String id,@RequestBody ImagenRequest imagenRequest){
		return service.actualizaImagenProducto(id, imagenRequest.getImg());
	}
}
