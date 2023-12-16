package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.TipoPagamento;

import java.time.LocalDate;

public record PagamentoResponseDTO(
        Double valor,
        Boolean confirmacaoPagamento,
        LocalDate dataConfirmacaoPagamento,

        TipoPagamento tipoPagamento,
        PedidoResponseDTO pedidoResponseDTO
) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento){
        return new PagamentoResponseDTO(
                pagamento.getValor(),
                pagamento.getConfirmacaoPagamento(),
                pagamento.getDataConfirmacaoPagamento(),
                pagamento.getTipoPagamento(),
                PedidoResponseDTO.valueOf(pagamento.getPedido())
        );
    }
}