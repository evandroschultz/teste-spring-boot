package br.com.springbot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

import br.com.springbot.service.FilmeService;
import br.com.springbot.service.ProdutorService;
import br.com.springbot.vo.Exibicao;
import br.com.springbot.vo.Resultado;

@RestController
@RequestMapping("/")
public class FilmesController {

    @Autowired
    FilmeService service;

    @Autowired
    ProdutorService produtorService;

    @Autowired
    FilmeService filmeService;

    
    @GetMapping("/produtoresFilmes")
    public List produtoresFilmes() {

        List list = produtorService.getListaProdutoresFilmes();

        return list;
    }

    @GetMapping("/vencedores")
    public List vencedores() {

        List listResultado = produtorService.getVencedores();
        return listResultado;
    }

    @GetMapping("/")
    public Exibicao exibir(){

        Exibicao exibicao = produtorService.getExibirResultado();

        return exibicao;
    }

    

}
