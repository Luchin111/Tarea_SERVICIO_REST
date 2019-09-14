package com.example.demo;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class PumakatariNotFoundException extends RuntimeException {
    public PumakatariNotFoundException(Long id) {
        super("No se pudo encontrar el Pumakatari " + id);
    }

}
