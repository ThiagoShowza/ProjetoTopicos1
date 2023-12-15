package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaDTO(
        @NotBlank(message = "O campo nome não pode ser nulo") String nome,

        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "O campo CPF deve estar formulado: 000.000.000-00") String cpf,
        LocalDate dataNascimento,
        Long idEndereco,
        List<TelefoneDTO> listaTelefone,
        Long idUsuario) {
}