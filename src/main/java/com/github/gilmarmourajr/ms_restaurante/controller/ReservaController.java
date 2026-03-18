package com.github.gilmarmourajr.ms_restaurante.controller;

import com.github.gilmarmourajr.ms_restaurante.dto.ReservaDTO;
import com.github.gilmarmourajr.ms_restaurante.entities.Reserva;
import com.github.gilmarmourajr.ms_restaurante.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas(){
        List<ReservaDTO> list = reservaService.findAllReservas();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id){
        ReservaDTO reservaDTO = reservaService.findReservaById(id);

        return ResponseEntity.ok(reservaDTO);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody @Valid ReservaDTO reservaDTO) {
        reservaDTO = reservaService.saveReserva(reservaDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(reservaDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(reservaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @RequestBody @Valid ReservaDTO reservaDTO){
        reservaDTO = reservaService.updateReserva(id, reservaDTO);

        return ResponseEntity.ok(reservaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        reservaService.deleteReservaById(id);

        return ResponseEntity.noContent().build();
    }
}
