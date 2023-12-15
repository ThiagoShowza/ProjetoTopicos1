package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.topicos1.model.Pessoa;


public record PessoaResponseDTO(
                String nome,
                String cpf,
                LocalDate dataNascimento,
                List<TelefoneResponseDTO> listaTelefone,
                EnderecoResponseDTO endereco,
                UsuarioResponseDTO usuario) {
        public static PessoaResponseDTO valueOf(Pessoa pessoa) {
                return new PessoaResponseDTO(
                                pessoa.getNome(),
                                pessoa.getCpf(),
                                pessoa.getDataNascimento(),
                                pessoa.getListaTelefone().stream()
                                .map(t -> TelefoneResponseDTO.valueOf(t)).toList(),
                                EnderecoResponseDTO.valueOf(pessoa.getEndereco()),
                                UsuarioResponseDTO.valueOf(pessoa.getUsuario()));
        }
}