package com.github.gilmarmourajr.ms_restaurante.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "tb_reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dataReserva;
    private String nomeCliente;
    private int qtdePessoas;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Restaurante restaurante;
}
