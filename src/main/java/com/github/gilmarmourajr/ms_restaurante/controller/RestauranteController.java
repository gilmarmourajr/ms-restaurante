package com.github.gilmarmourajr.ms_restaurante.controller;

import com.github.gilmarmourajr.ms_restaurante.dto.RestauranteDTO;
import com.github.gilmarmourajr.ms_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
