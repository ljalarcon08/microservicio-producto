package com.example.la.producto.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

@NoRepositoryBean
public interface GenericRepo<T> extends ReactiveMongoRepository<T,String>,ReactiveSortingRepository<T, String>{

}
