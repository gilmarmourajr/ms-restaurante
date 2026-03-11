package com.github.gilmarmourajr.ms_restaurante.service;

import com.github.gilmarmourajr.ms_restaurante.dto.ReservaDTO;
import com.github.gilmarmourajr.ms_restaurante.entities.Reserva;
import com.github.gilmarmourajr.ms_restaurante.entities.Restaurante;
import com.github.gilmarmourajr.ms_restaurante.exceptions.DatabaseException;
import com.github.gilmarmourajr.ms_restaurante.exceptions.ResourceNotFoundException;
import com.github.gilmarmourajr.ms_restaurante.repositories.ReservaRepository;
import com.github.gilmarmourajr.ms_restaurante.repositories.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<ReservaDTO> findAllReservas(){
        List<Reserva> reservas = reservaRepository.findAll();

        return reservas.stream().map(ReservaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ReservaDTO findReservaById(Long id){
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id));

        return new ReservaDTO(reserva);
    }

    @Transactional
    public ReservaDTO saveReserva(ReservaDTO reservaDTO){
        try {
            Reserva reserva = new Reserva();

            copyDtoToReserva(reservaDTO, reserva);
            reserva = reservaRepository.save(reserva);
            return new ReservaDTO(reserva);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível salvar Reserva. Restaurante inexistente (ID: " +
                    reservaDTO.getRestaurante().getId());
        }
    }

    @Transactional
    public ReservaDTO updateReserva(Long id, ReservaDTO reservaDTO){
        try {
            Reserva reserva = reservaRepository.getReferenceById(id);
            copyDtoToReserva(reservaDTO, reserva);
            reserva = reservaRepository.save(reserva);
            return new ReservaDTO(reserva);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recursos não encontrados. ID: " + id);
        }
    }

    @Transactional
    public void deleteReservaById(Long id){
        if(!reservaRepository.existsById(id)){
            throw new ResourceNotFoundException("Recursos não encontrados. ID: " + id);
        }
        reservaRepository.deleteById(id);
    }

    private void copyDtoToReserva(ReservaDTO reservaDTO, Reserva reserva) {
        reserva.setDataReserva(reservaDTO.getDataReserva());
        reserva.setNomeCliente(reservaDTO.getNomeCliente());
        reserva.setQtdePessoas(reservaDTO.getQtdePessoas());

        Restaurante restaurante =restauranteRepository.getReferenceById(reservaDTO.getRestaurante().getId());

        reserva.setRestaurante(restaurante);
    }
}
