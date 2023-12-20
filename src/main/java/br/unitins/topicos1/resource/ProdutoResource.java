package br.unitins.topicos1.resource;

import java.io.File;
import java.io.IOException;

import br.unitins.topicos1.service.ProdutoFileService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.ProdutoDTO;

import br.unitins.topicos1.form.ProdutoImageForm;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.service.ProdutoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;


@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @Inject
    ProdutoFileService fileService;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(@Valid ProdutoDTO dto) {
        LOG.infof("Iniciando o processo de inserçao do produto %s", dto.getNome());
        return Response.status(Status.CREATED).entity(produtoService.insert(dto)).build();
    }

    @PUT
    @RolesAllowed({"Admin"})
    @Transactional
    @Path("/{id}")
    public Response update(ProdutoDTO dto, @PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de update do produto %s", dto.getNome());
        produtoService.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Iniciando o processo de delete do produto %s");
        produtoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({"User", "Admin"})
    public Response findAll() {
        LOG.infof("buscando todos os produtos");
        return Response.ok(produtoService.findByAll()).build();
    }

    @GET
    @RolesAllowed({ "Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("buscando todos o prduto por id");
        return Response.ok(produtoService.findById(id)).build();
    }

    @PATCH
    @Path("/upload/imagem/{id}")
    @RolesAllowed({ "Admin"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ProdutoImageForm form, @PathParam("id") Long id) throws IOException {
        String nomeImagem;
        nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        ProdutoResponseDTO produtoDTO = produtoService.findById(id);
        produtoDTO = produtoService.updateNomeImagem(produtoDTO.id(), nomeImagem);

        return Response.ok(produtoDTO).build();

    }

@GET
@Path("/download/imagem/{nomeImagem}")
@RolesAllowed({"Admin"})
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public Response download(@PathParam("nomeImagem") String nomeImagem) {
    // Obtain the File object corresponding to the requested image name
    File file = fileService.obter(nomeImagem);
    
    // Create a ResponseBuilder with the file content
    ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
    
    // Set the Content-Disposition header to specify the filename for the downloaded file
    response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
    
    // Build and return the Response object
    return response.build();
}


}