package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CartaoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;

import java.util.List;

public interface PagamentoService {

    public PagamentoResponseDTO pagarCartao(CartaoDTO dto);
}