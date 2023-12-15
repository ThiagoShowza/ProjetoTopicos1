package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String senha,
        Perfil perfil) {
            
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getPerfil());
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Long getId() {
        return id;
    }


    // public record PerfilDTO(Integer id, String label) {
    //     public static PerfilDTO valueOf(Perfil perfil) {
    //         return new PerfilDTO(perfil.getId(), perfil.getLabel());
    //     }
    // }

    

}