package br.unitins.topicos1.model;

import jakarta.persistence.Entity;

@Entity
public class Usuario extends DefaultEntity {

    //teste alrresrtfd
    
    private String nome;
    private String login;
    private String senha;


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }     
}