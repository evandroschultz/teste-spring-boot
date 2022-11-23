package br.com.springbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbot.models.Concurso;
import br.com.springbot.service.ConcursoService;

@RestController
@RequestMapping("/concursos")
public class concursoController {
    
    @Autowired
    ConcursoService concursoService;

    @GetMapping("/")
    public String hellWorld(){
        System.out.println("olá, nasci!");
        return "Hey nasci!";
    }

    @PostMapping("/insert")
    public Concurso cadastrarConcurso(@RequestBody Concurso concurso){
        
        //Concurso concurso = new Concurso();
        //concurso.setNome("OScar");

        concursoService.salvar(concurso);
        
        return concurso;
    }

}
