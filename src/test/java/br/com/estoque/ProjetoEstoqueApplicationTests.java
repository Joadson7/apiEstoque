package br.com.estoque;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjetoEstoqueApplicationTests {

    @LocalServerPort
    private int port;

    static String produtoId;
    static String entradaId;
    static String saidaId;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    void deveCriarProdutoComSucesso() {
        produtoId =
            given()
                .contentType(ContentType.JSON)
                .body("{" +
                      "\"nome\":\"Caneta Azul\"," +
                      "\"unidade\":\"UN\"," +
                      "\"categoria\":\"FERRAMENTAS\"}")
            .when()
                .post("/api/v1/produtos")
            .then()
                .statusCode(201)
                .body("nome", equalTo("Caneta Azul"))
                .extract()
                .path("id").toString();
    }

    @Test
    @Order(2)
    void deveRegistrarEntradaDeProduto() {
        entradaId =
            given()
                .contentType(ContentType.JSON)
                .body("{" +
                      "\"produtoId\":\"" + produtoId + "\"," +
                      "\"quantidade\":15," +
                      "\"dataEntrada\":\"2025-06-30\"," +
                      "\"fornecedor\":\"Fornecedor A\"}")
            .when()
                .post("/api/v1/entradas")
            .then()
                .statusCode(201)
                .body("quantidade", equalTo(15))
                .extract()
                .path("id").toString();
    }

    @Test
    @Order(3)
    void deveRegistrarSaidaDeProduto() {
        saidaId =
            given()
                .contentType(ContentType.JSON)
                .body("{" +
                      "\"produtoId\":\"" + produtoId + "\"," +
                      "\"quantidade\":5," +
                      "\"dataSaida\":\"2025-06-30\"," +
                      "\"destino\":\"ALMOXARIFADO\"}")
            .when()
                .post("/api/v1/saidas")
            .then()
                .statusCode(201)
                .body("quantidade", equalTo(5))
                .extract()
                .path("id").toString();
    }

    @Test
    @Order(4)
    void deveListarProdutos() {
        when()
            .get("/api/v1/produtos")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    @Order(5)
    void deveBuscarProdutoPorId() {
        when()
            .get("/api/v1/produtos/" + produtoId)
        .then()
            .statusCode(200)
            .body("id", equalTo(produtoId));
    }

    @Test
    @Order(6)
    void deveAtualizarProduto() {
        given()
            .contentType(ContentType.JSON)
            .body("{" +
                  "\"nome\":\"Caneta Vermelha\"," +
                  "\"unidade\":\"UN\"," +
                  "\"categoria\":\"FERRAMENTAS\"}")
        .when()
            .put("/api/v1/produtos/" + produtoId)
        .then()
            .statusCode(200)
            .body("nome", equalTo("Caneta Vermelha"));
    }

    @Test
    @Order(7)
    void deveDesativarProduto() {
        when()
            .delete("/api/v1/produtos/" + produtoId)
        .then()
            .statusCode(204);
    }
}