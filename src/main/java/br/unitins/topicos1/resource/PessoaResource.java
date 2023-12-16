package br.unitins.topicos1.resource;



import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.PessoaDTO;
import br.unitins.topicos1.dto.PessoaResponseDTO;
import br.unitins.topicos1.service.EnderecoServiceImpl;
import br.unitins.topicos1.service.PessoaService;
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


@Path("/pessoa")    
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    @Inject
    PessoaService service;
    @Inject
    EnderecoServiceImpl enderecoImpl;


    private static final Logger LOG = Logger.getLogger(PessoaResource.class);

    @POST
    @RolesAllowed({"User","Admin"})
    public Response insert(@Valid PessoaDTO dto){
        PessoaResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed({"User","Admin"})
    @Path("/{id}")
    public Response update (PessoaDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @RolesAllowed({"Admin"})
    @Path("/search/endereco/{enderecoId}")
    public Response findByEnderecoId(@PathParam("enderecoId") Long enderecoId) {
        List<PessoaResponseDTO> pessoas = service.findByEnderecoId(enderecoId);
        return Response.ok(pessoas).build();
    }


    @GET
    @RolesAllowed({"Admin"})
    public Response findAll(){
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }
}