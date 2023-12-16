package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CartaoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService{

    @Inject
    PagamentoRepository repository;

    @Inject
    PedidoRepository pedidoRepository;
    @Override
    @Transactional
    public PagamentoResponseDTO pagarCartao(CartaoDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setConfirmacaoPagamento(true);
        pagamento.setDataConfirmacaoPagamento(LocalDate.now());
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido());
        pagamento.setPedido(pedido);
        pagamento.setTipoPagamento(TipoPagamento.PIX);
        pagamento.setValor(pedido.getTotalPedido());

        repository.persist(pagamento);

        return PagamentoResponseDTO.valueOf(pagamento);
    }
}
