package com.example.demo;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PumakatariController {

    private final PumakatariRepository repository;


    PumakatariController(PumakatariRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Pumakatari")
    List<Pumakatari> all() {
        return repository.findAll();
    }

    @PostMapping("/Pumakatari")
    Pumakatari newEmployee(@RequestBody Pumakatari newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/Pumakatari/{id}")
    Pumakatari one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PumakatariNotFoundException(id));
    }

    @PutMapping("/Pumakatari/{id}")
    Pumakatari replaceEmployee(@RequestBody Pumakatari newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(pumakatari -> {
                    pumakatari.setPlaca(newEmployee.getPlaca());
                    pumakatari.setRuta(newEmployee.getRuta());
                    return repository.save(pumakatari);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/Pumakatari/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}