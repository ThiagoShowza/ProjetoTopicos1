package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record CidadeDTO (
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    Long idEstado
){


}