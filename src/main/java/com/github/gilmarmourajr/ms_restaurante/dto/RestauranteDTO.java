package com.github.gilmarmourajr.ms_restaurante.dto;

import com.github.gilmarmourajr.ms_restaurante.entities.Restaurante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class RestauranteDTO {
    private Long id;

    @NotBlank(message = "Campo nome é requerido")
    @Size(min = 5, max = 120, message = "O nome deve ter entre 5 e 120 caracteres")
    private String nome;

    @NotBlank(message = "Campo endereço é requerido")
    @Size(min = 5, max = 120, message = "O endereço deve ter entre 5 e 120 caracteres")
    private String endereco;

    @NotBlank(message = "Campo cidade é requerido")
    @Size(min = 3, max = 100, message = "A cidade deve ter entre 3 e 100 caracteres")
    private String cidade;

    @NotNull(message = "Campo UF é requerido")
    private String uf;

    public RestauranteDTO(Restaurante restaurante) {
        id = restaurante.getId();
        nome = restaurante.getNome();
        endereco = restaurante.getEndereco();
        cidade = restaurante.getCidade();
        uf = restaurante.getUf();
    }
}
