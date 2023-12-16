package br.unitins.topicos1;

import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import io.restassured.response.Response;
import br.unitins.topicos1.dto.LoginDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.JwtService;
import br.unitins.topicos1.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class UsuarioResourceTest {

        @Inject
        HashService hashService;

        @Inject
        UsuarioService usuarioService;

        @Inject
        JwtService jwtService;

        ObjectMapper objectMapper = new ObjectMapper();

        @Test
        public void testInsert() {
                UsuarioDTO usuarioDTO = new UsuarioDTO("Teste", "1234", 1);
                given()
                                .contentType(ContentType.JSON)
                                .body(usuarioDTO)
                                .when().post("/usuarios")
                                .then()
                                .statusCode(201)
                                .body(
                                                "login", is("Teste"),
                                                "senha",
                                                is("O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA=="));
                ;

        }

        @Test
        public void testUpdate() {

                UsuarioDTO usuarioDTO = new UsuarioDTO(
                                "maria.user",
                                "1234",
                                1);

                String hashSenha = hashService.getHashSenha(usuarioDTO.senha());
                UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(usuarioDTO.login(),
                                hashSenha.toString());

                String tokenUser = jwtService.generateJwt(result);

                given()
                                .headers("Authorization", "Bearer " + tokenUser)
                                .contentType(ContentType.JSON)
                                .body(usuarioDTO)
                                .when()
                                .put("/usuarios/1")
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testDelete() {
  
          UsuarioDTO usuarioDTO = new UsuarioDTO("teste", "1234", 2);

          UsuarioResponseDTO createdUser = given()
                  .contentType("application/json")
                  .body(usuarioDTO)
                  .when()
                  .post("/usuarios")
                  .then()
                  .statusCode(201)
                  .extract()
                  .as(UsuarioResponseDTO.class);

      }


        @Test
        public void testFindAll() {

                UsuarioDTO usuarioDTO = new UsuarioDTO("Teste", "1234", 1);

                given()
                                .contentType(ContentType.JSON)
                                .body(usuarioDTO)
                                .when().post("/usuarios")
                                .then()
                                .statusCode(201);

                // Agora você pode testar o método findAll
                given()
                                .when().get("/usuarios")
                                .then()
                                .statusCode(200);

        }

        @Test
        public void testFindById() {
                // Insira um registro de teste para testar o findById
                UsuarioDTO usuarioDTO = new UsuarioDTO("Teste", "1234", 1);

                Response insertResponse = given()
                                .contentType(ContentType.JSON)
                                .body(usuarioDTO)
                                .when().post("/usuarios");

                insertResponse.then()
                                .statusCode(201);
                Long id = insertResponse.jsonPath().getLong("id");

                // Agora teste o método findById
                given()
                                .when()
                                .get("/usuarios/" + id)
                                .then()
                                .statusCode(200)
                                .body("id", equalTo(id.intValue()));
        }

}
