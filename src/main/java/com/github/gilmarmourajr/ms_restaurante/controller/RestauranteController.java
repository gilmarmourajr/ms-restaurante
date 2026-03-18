package com.github.gilmarmourajr.ms_restaurante.controller;

import com.github.gilmarmourajr.ms_restaurante.dto.RestauranteDTO;
import com.github.gilmarmourajr.ms_restaurante.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")

public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> getAllRestaurantes(){
        List<RestauranteDTO> restaurantes = restauranteService.findAllRestaurantes();

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@PathVariable Long id){
        RestauranteDTO restauranteDTO = restauranteService.findRestauranteById(id);

        return ResponseEntity.ok(restauranteDTO);
    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> createRestaurante(@Valid @RequestBody RestauranteDTO restauranteDTO){
        restauranteDTO = restauranteService.saveRestaurante(restauranteDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(restauranteDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(restauranteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> updateRestaurante(@PathVariable Long id, @RequestBody @Valid RestauranteDTO restauranteDTO){
        restauranteDTO = restauranteService.updateRestaurante(id, restauranteDTO);
        return ResponseEntity.ok(restauranteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id){
        restauranteService.deleteRestauranteById(id);
        return ResponseEntity.noContent().build();
    }
}
