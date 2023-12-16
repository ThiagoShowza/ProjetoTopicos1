package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.PessoaDTO;
import br.unitins.topicos1.dto.PessoaResponseDTO;
import br.unitins.topicos1.dto.ProdutoDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoService {

    public ProdutoResponseDTO insert(ProdutoDTO dto);

    public ProdutoResponseDTO update(ProdutoDTO dto, Long id);


    public ProdutoResponseDTO updateNomeImagem(Long id, String nomeImagem) ;

    public void delete(Long id);

    public ProdutoResponseDTO findById(Long id);

    public List<ProdutoResponseDTO> findByAll();


}
