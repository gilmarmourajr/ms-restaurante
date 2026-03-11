package com.github.gilmarmourajr.ms_restaurante.repositories;

import com.github.gilmarmourajr.ms_restaurante.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
