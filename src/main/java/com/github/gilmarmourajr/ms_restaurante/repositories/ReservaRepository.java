package com.github.gilmarmourajr.ms_restaurante.repositories;

import com.github.gilmarmourajr.ms_restaurante.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
