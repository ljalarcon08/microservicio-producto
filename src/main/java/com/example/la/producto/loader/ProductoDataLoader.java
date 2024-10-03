package com.example.la.producto.loader;

import com.example.la.producto.document.Catalogo;
import com.example.la.producto.document.Producto;
import com.example.la.producto.repository.CatalogoRepository;
import com.example.la.producto.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoDataLoader {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());



    @PostConstruct
    public void loadData() {

        loadCatalogo();

        productoRepository.count().subscribe(count ->{
            if(count.equals(0L)){
                Resource resource = resourceLoader.getResource("classpath:" + "producto.json");
                InputStream in = null;
                try {
                    in = resource.getInputStream();
                    String json = new BufferedReader(new InputStreamReader(in))
                            .lines().collect(Collectors.joining("\n"));
                    ObjectMapper mapper = new ObjectMapper();
                    List<Producto> productos = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Producto.class));
                    productoRepository.saveAll(productos).subscribe();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void loadCatalogo() {

        Long countCat=catalogoRepository.count().block();

        if(countCat.equals(0L)){
            Resource resource = resourceLoader.getResource("classpath:" + "catalogo.json");
            InputStream in = null;
            try {
                in = resource.getInputStream();
                String json = new BufferedReader(new InputStreamReader(in))
                        .lines().collect(Collectors.joining("\n"));
                ObjectMapper mapper = new ObjectMapper();
                List<Catalogo> catalogos = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Catalogo.class));
                catalogoRepository.saveAll(catalogos).blockLast();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
