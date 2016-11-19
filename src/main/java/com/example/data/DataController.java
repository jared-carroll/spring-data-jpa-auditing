package com.example.data;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
public class DataController {
    private DataRepository dataRepository;

    public DataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @PostMapping("/data")
    public ResponseEntity<Void> create(@RequestBody Data data) {
        data = dataRepository.save(data);
        URI uri = MvcUriComponentsBuilder
                .fromMethodCall(on(DataController.class).show(data.getId()))
                .build()
                .toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }

    @GetMapping("/data/{id}")
    public Data show(@PathVariable long id) {
        return null;
    }
}
