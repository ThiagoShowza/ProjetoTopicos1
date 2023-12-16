package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Pessoa;
import br.unitins.topicos1.repository.CidadeRepository;
import br.unitins.topicos1.repository.EnderecoRepository;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    UsuarioServiceImpl usuarioService;

    @Inject
    PessoaServiceImpl pessoaService;

    @Override
    @Transactional
    public EnderecoResponseDTO insert(EnderecoDTO dto) {
        Endereco novoEndereco = new Endereco();
        Cidade cidade = cidadeRepository.findById(dto.idCidade());
        novoEndereco.setCidade(cidade);
        novoEndereco.setBairro(dto.bairro());
        novoEndereco.setQuadra(dto.quadra());
        novoEndereco.setLote(dto.lote());
        novoEndereco.setRua(dto.rua());

        repository.persist(novoEndereco);

        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(EnderecoDTO dto, Long id) {
        Endereco enderecoUpdate = repository.findById(id);

        UsuarioResponseDTO usuarioLogado = usuarioService.findMyUser();
        Pessoa pessoa = pessoaService.findByEnderecoId(enderecoUpdate.getId());
        if(usuarioLogado.perfil().equals(Perfil.ADMIN) || usuarioLogado.getId().equals(pessoa.getUsuario().getId())){
           // Atualizar endereço
        }else{
            throw new ForbiddenException("Você não tem permissão para atualizar este endereço.");
        }

        if (enderecoUpdate != null) {
            Cidade cidade = cidadeRepository.findById(dto.idCidade());
            enderecoUpdate.setCidade(cidade);
            enderecoUpdate.setBairro(dto.bairro());
            enderecoUpdate.setQuadra(dto.quadra());
            enderecoUpdate.setLote(dto.lote());
            enderecoUpdate.setRua(dto.rua());
        } else
            throw new NotFoundException();

        return EnderecoResponseDTO.valueOf(enderecoUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        return EnderecoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<EnderecoResponseDTO> findByNome(String bairro) {
        return repository.findByBairro(bairro).stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EnderecoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

}