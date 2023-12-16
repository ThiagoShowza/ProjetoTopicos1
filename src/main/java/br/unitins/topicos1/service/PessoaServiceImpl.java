package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.PessoaDTO;
import br.unitins.topicos1.dto.PessoaResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Pessoa;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.PessoaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

    @Inject
    PessoaRepository repository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public PessoaResponseDTO insert(PessoaDTO dto) {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(dto.nome());
        novaPessoa.setCpf(dto.cpf());
        novaPessoa.setDataNascimento(dto.dataNascimento());
        Endereco endereco = enderecoRepository.findById(dto.idEndereco());
        novaPessoa.setEndereco(endereco);
        if (dto.listaTelefone() != null &&
                !dto.listaTelefone().isEmpty()) {
            novaPessoa.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.getCodigoArea());
                telefone.setNumero(tel.getNumero());
                novaPessoa.getListaTelefone().add(telefone);
            }
        }
        Usuario usuario = usuarioRepository.findById(dto.idUsuario());
        novaPessoa.setUsuario(usuario);

        repository.persist(novaPessoa);

        return PessoaResponseDTO.valueOf(novaPessoa);
    }

    @Override
    @Transactional
    public PessoaResponseDTO update(PessoaDTO dto, Long id) {

        Pessoa pessoaUpdate = repository.findById(id);
        if (pessoaUpdate != null) {
        pessoaUpdate.setNome(dto.nome());
        pessoaUpdate.setCpf(dto.cpf());
        pessoaUpdate.setDataNascimento(dto.dataNascimento());
        Endereco endereco = enderecoRepository.findById(dto.idEndereco());
        pessoaUpdate.setEndereco(endereco);
        if (dto.listaTelefone() != null &&
                !dto.listaTelefone().isEmpty()) {
            pessoaUpdate.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.getCodigoArea());
                telefone.setNumero(tel.getNumero());
                pessoaUpdate.getListaTelefone().add(telefone);
            }
        }
        Usuario usuario = usuarioRepository.findById(dto.idUsuario());
        pessoaUpdate.setUsuario(usuario);

        repository.persist(pessoaUpdate);
        } else
            throw new NotFoundException();

        return PessoaResponseDTO.valueOf(pessoaUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }


    @Override
    public List<PessoaResponseDTO> findByEnderecoId(Long enderecoId) {
        return repository.findByEnderecoId(enderecoId).stream()
                .map(PessoaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public PessoaResponseDTO findById(Long id) {
        return PessoaResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<PessoaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PessoaResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

}