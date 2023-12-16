package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PessoaDTO;
import br.unitins.topicos1.dto.PessoaResponseDTO;

public interface PessoaService {
    
    public PessoaResponseDTO insert(PessoaDTO dto);

    public PessoaResponseDTO update(PessoaDTO dto, Long id);

    public void delete(Long id);

    public PessoaResponseDTO findById(Long id);

    public List<PessoaResponseDTO> findByNome(String nome);

    public List<PessoaResponseDTO> findByAll(); 

    public List<PessoaResponseDTO> findByEnderecoId(Long enderecoId);
    

}
