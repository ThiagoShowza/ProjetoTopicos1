package br.unitins.topicos1.dto;

public record EnderecoDTO(
        Long idCidade,
        String bairro,
        String quadra,
        String rua,
        String lote) {
            
}