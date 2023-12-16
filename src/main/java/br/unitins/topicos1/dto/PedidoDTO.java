package br.unitins.topicos1.dto;


import java.util.List;

public record PedidoDTO (
    List<ItemPedidoDTO> itens
) {

}
