package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {
    public List<Pessoa> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%" + nome + "%").list();
    }

    public List<Pessoa> findByEnderecoId(Long enderecoId) {
        return find("endereco.id = ?1", enderecoId).list();
    }
}