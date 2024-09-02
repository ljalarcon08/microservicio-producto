package com.example.la.producto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.la.producto.document.Catalogo;
import com.example.la.producto.repository.CatalogoRepository;
import com.example.la.producto.repository.GenericRepo;
import com.example.la.producto.service.CatalogoService;

import reactor.core.publisher.Mono;

@Service
public class CatalogoServiceImpl extends GenericServImpl<Catalogo> implements CatalogoService{

	@Autowired
	private CatalogoRepository repository;
	
	@Override
	public GenericRepo<Catalogo> getRepository() {
		return repository;
	}

	@Override
	public Mono<Catalogo> actualizaImagenCatalogo(String id, String url) {
		return repository.findById(id).map(catalogo->{
			catalogo.setUrl(url);
			return catalogo;
		}).flatMap(catalogo->repository.save(catalogo));
	}

}
