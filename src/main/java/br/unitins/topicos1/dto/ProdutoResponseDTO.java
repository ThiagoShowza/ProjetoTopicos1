package br.unitins.topicos1.dto;


import br.unitins.topicos1.model.Produto;


public record ProdutoResponseDTO(
    Long id,
    String nome,
    Double preco,
    Integer estoque,

    String nomeImagem

) { 
    public static ProdutoResponseDTO valueOf(Produto produto){
        return new ProdutoResponseDTO(
            produto.getId(), 
            produto.getNome(),
            produto.getPreco(),
            produto.getEstoque(),
            produto.getNomeImagem()
        );
    }
}