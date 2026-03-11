package com.github.gilmarmourajr.ms_restaurante.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FieldMessageDTO {

    private String fieldName;
    private String message;
}
