package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;


public class ProdutoDTO {

    @NotBlank(message = "O campo nome não pode ser nulo")
    private String nome;


    private final String nomeImagem;
    private Double preco;
    private Integer estoque;

    // CONSTRUTOR PADRÃO
    public ProdutoDTO(@NotBlank(message = "O campo nome não pode ser nulo") String nome, String nomeImagem, Double preco,
                      Integer estoque) {
        this.nome = nome;
        this.nomeImagem = nomeImagem;
        this.preco = preco;
        this.estoque = estoque;

        

    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Double getPreco() {
        return preco;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    // HASH CODE
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((preco == null) ? 0 : preco.hashCode());
        result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProdutoDTO other = (ProdutoDTO) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (preco == null) {
            if (other.preco != null)
                return false;
        } else if (!preco.equals(other.preco))
            return false;
        if (estoque == null) {
            if (other.estoque != null)
                return false;
        } else if (!estoque.equals(other.estoque))
            return false;
        if (nomeImagem == null) {
            if (other.nomeImagem != null)
                return false;
        } else if (!nomeImagem.equals(other.nomeImagem))
            return false;
        return true;
    }
}

