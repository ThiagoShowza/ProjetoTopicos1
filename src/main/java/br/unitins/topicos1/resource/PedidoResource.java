package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.service.PedidoService;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken jwt;

private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @RolesAllowed({"User"})
    public Response insert(PedidoDTO dto) {
        LOG.infof("iniciando o processo de adicionar um pedido");

        String login = jwt.getSubject();
        
        PedidoResponseDTO retorno = service.insert(dto, login);
        return Response.status(201).entity(retorno).build();
    }

    @GET
    @RolesAllowed({"User", "Admin"})
    public Response findAll() {
        LOG.infof("buscando todos os pedidos");
        
        return Response.ok(service.findByAll()).build();
    }

}