package com.github.gilmarmourajr.ms_restaurante.dto;

import com.github.gilmarmourajr.ms_restaurante.entities.Reserva;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ReservaDTO {
    private Long id;

    @NotNull(message = "O campo dataReserva é requerido")
    private LocalDate dataReserva;

    @NotBlank(message = "O campo nomeCliente é requerido")
    @Size(min = 3, max = 100, message = "O nomeCliente deve ter entre 3 e 100 caracteres")
    private String nomeCliente;

    @NotNull(message = "O campo qtdePessoas é requerido")
    @Positive(message = "O valor deve ser um número positivo (maior que zero '0')")
    private int qtdePessoas;

    @NotNull(message = "O campo restaurante é requerido")
    private RestauranteDTO restaurante;

    public ReservaDTO(Reserva reserva) {
        id = reserva.getId();
        dataReserva = reserva.getDataReserva();
        nomeCliente = reserva.getNomeCliente();
        qtdePessoas = reserva.getQtdePessoas();
        restaurante = new RestauranteDTO(reserva.getRestaurante());
    }
}
