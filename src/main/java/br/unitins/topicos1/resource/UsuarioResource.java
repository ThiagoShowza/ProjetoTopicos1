package br.unitins.topicos1.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @Inject
    JsonWebToken jwt;

    @POST//INSERIR USUARIO
    public Response insert(UsuarioDTO dto){
         return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT//AUTUALIZAR USUARIO
    @Transactional
    @RolesAllowed({"User","Admin"})
    @Path("/{id}")
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE//DELETAR USUARIO
    @Transactional
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
 
    @GET//LISTAR TODOS
    @RolesAllowed({"Admin"})
    public Response findAll() {
        return Response.ok(service.findByAll()).build();
    }

    @GET//LISTAR POR ID
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
       return Response.ok(service.findById(id)).build();
    }
    
    @GET//LISTAR POR NOME
    @RolesAllowed({"Admin"})
    @Path("/search/login/{login}")
    public Response findByNome(@PathParam("login") String login) {
        return Response.ok(service.findByNome(login)).build();
    }

    @GET//LISTAR MEU USUÁRIO
    @RolesAllowed({"User","Admin"})
    @Path("/my-user")
    public Response findMyUser() {
       return Response.ok(service.findMyUser()).build();
    }
}
