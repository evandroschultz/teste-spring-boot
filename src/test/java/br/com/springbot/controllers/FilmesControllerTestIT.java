package br.com.springbot.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import br.com.springbot.models.Produtor;
import br.com.springbot.service.ProdutorService;
import br.com.springbot.vo.Exibicao;
import br.com.springbot.vo.Resultado;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DisplayName("Classe de teste")
public class FilmesControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    HttpHeaders headers = new HttpHeaders();
    Gson gson = new Gson();

    @Test
    @DisplayName("Testa se o valor esperado é obtido")
    public void getResultadoTest() throws Exception {

        String resultadoEsperado = this.getJson();
        System.out.println(resultadoEsperado);

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = this.testRestTemplate
                .exchange("/", HttpMethod.GET, httpEntity, String.class);

        JSONAssert.assertEquals(resultadoEsperado, response.getBody(), false);
        System.out.println("corpo" + response.getBody());

    }

    @Test
    @DisplayName("Testa se o produto é adicionado ao bd")
    public void addProdutorTest() {

        Produtor produtor = new Produtor();
        produtor.setNome("Evandro Schultz");

        HttpEntity<Produtor> httpEntity = new HttpEntity<>(produtor);

        ResponseEntity<Produtor> response = this.testRestTemplate
                .exchange("/produtor/", HttpMethod.POST, httpEntity, Produtor.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);       
    }

    @Test
    @DisplayName("Testa se um produtor é recuperado por ID")
    public void getProdutorPorIdTest() {

        ResponseEntity<Produtor> response = this.testRestTemplate
                .exchange("/produtor/" + 10, HttpMethod.GET, null, Produtor.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    @DisplayName("Teste se uma lista de produtores é recuperada")
    public void getProdutoresTest() {
        ResponseEntity<Produtor[]> response = this.testRestTemplate
                .exchange("/produtor/", HttpMethod.GET, null, Produtor[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    @DisplayName("Testa se um produto é removido")
    public void removerProdutorTest() {
        ResponseEntity<Produtor> response = this.testRestTemplate
                .exchange("/produtor/" + 10, HttpMethod.DELETE, null, Produtor.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    public String getJson() {
        // facilita gerar o resultado para o teste
        Resultado min = new Resultado();
        Resultado max = new Resultado();

        min.setProducer("Joel Silver");
        min.setInterval(1);
        min.setPreviousWin(1990);
        min.setFollowingWin(1991);

        max.setProducer("Matthew Vaughn");
        max.setInterval(13);
        max.setPreviousWin(2002);
        max.setFollowingWin(2015);
        Exibicao exibicao = new Exibicao();
        exibicao.getMax().add(max);
        exibicao.getMin().add(min);

        System.out.println(gson.toJson(exibicao));
        return this.gson.toJson(exibicao);

    }

}
