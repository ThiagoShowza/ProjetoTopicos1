package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        CidadeResponseDTO cidade,
        String bairro,
        String quadra,
        String rua,
        String lote) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                CidadeResponseDTO.valueOf(endereco.getCidade()),
                endereco.getBairro(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getLote());
    }
}