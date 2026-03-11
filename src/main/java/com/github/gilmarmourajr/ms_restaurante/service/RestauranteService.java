package com.github.gilmarmourajr.ms_restaurante.service;

import com.github.gilmarmourajr.ms_restaurante.dto.RestauranteDTO;
import com.github.gilmarmourajr.ms_restaurante.entities.Restaurante;
import com.github.gilmarmourajr.ms_restaurante.exceptions.ResourceNotFoundException;
import com.github.gilmarmourajr.ms_restaurante.repositories.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<RestauranteDTO> findAllRestaurantes(){
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        return restaurantes.stream().map(RestauranteDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public RestauranteDTO findRestauranteById(Long id){
        Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id));

        return new RestauranteDTO(restaurante);
    }

    @Transactional
    public RestauranteDTO saveRestaurante(RestauranteDTO restauranteDTO){
        Restaurante restaurante = new Restaurante();
        copyDtoToRestaurante(restauranteDTO, restaurante);
        restaurante = restauranteRepository.save(restaurante);

        return new RestauranteDTO(restaurante);
    }

    @Transactional
    public RestauranteDTO updateRestaurante(Long id, RestauranteDTO inputDTO){
        try {
            Restaurante restaurante = new Restaurante();
            copyDtoToRestaurante(inputDTO, restaurante);
            restaurante = restauranteRepository.save(restaurante);
            return new RestauranteDTO(restaurante);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteRestauranteById(Long id) {
        if(!restauranteRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID:" + id);
        }
        try {
            restauranteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não foi possível excluir o restaurante. " +
                    "Existem reservas associadas a ele");
        }
    }

    private void copyDtoToRestaurante(RestauranteDTO restauranteDTO, Restaurante restaurante) {
        restaurante.setNome(restauranteDTO.getNome());
        restaurante.setEndereco(restauranteDTO.getEndereco());
        restaurante.setCidade(restauranteDTO.getCidade());
        restaurante.setUf(restauranteDTO.getUf());
    }
}
