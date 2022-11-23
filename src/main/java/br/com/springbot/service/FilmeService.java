package br.com.springbot.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springbot.models.Filme;
import br.com.springbot.models.Produtor;
import br.com.springbot.repository.FilmeRepository;
import br.com.springbot.repository.ProdutorRepository;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository repository;

    public List getFilmes() {

        //List filmes = new ArrayList<>();
        //repository.findAll().forEach(filme -> filmes.add(filme));
        //return filmes;

        return repository.findAll();

    }

    

    public Filme getFilmePorId(Long id) {
        Filme filme = repository.findById(id).get();
        return filme;
    }

    public void salvar(Filme filme) {
        repository.save(filme);
    }

    public void delete(Filme filme) {
        repository.delete(filme);
    }

}
