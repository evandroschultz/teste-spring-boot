package br.com.springbot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbot.service.FilmeService;
import br.com.springbot.service.ProdutorService;
import br.com.springbot.vo.Exibicao;

@RestController
@RequestMapping("/filmes")
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
