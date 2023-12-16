package br.unitins.topicos1.dto;


import br.unitins.topicos1.model.BandeiraCartao;
import jakarta.validation.constraints.NotBlank;


public class CartaoDTO {

    private Long idPedido;
    @NotBlank(message = "O campo nome não pode ser nulo")
    private String nome;
    private String numero;
    private String dataValidade ;
    private BandeiraCartao bandeiraCartao;
    private Integer cvc;

    // CONSTRUTOR PADRÃO
    public CartaoDTO(Long idPedido, @NotBlank(message = "O campo nome não pode ser nulo") String nome, String numero, String dataValidade,
           BandeiraCartao bandeiraCartao, Integer cvc) {
        this.idPedido = idPedido;
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.numero = numero;
        this.cvc = cvc;
        this.bandeiraCartao = bandeiraCartao;

    }

    public Long getIdPedido() {
        return idPedido;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }

    public Integer getCvc() {
        return cvc;
    }
}