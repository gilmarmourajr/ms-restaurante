package com.github.gilmarmourajr.ms_restaurante.controller;

import com.github.gilmarmourajr.ms_restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;
}
