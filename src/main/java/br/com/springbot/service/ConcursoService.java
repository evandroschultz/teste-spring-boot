package br.com.springbot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.springbot.repository.ConcursoRepository;
import br.com.springbot.models.Concurso;

@Service
public class ConcursoService {

    @Autowired
    ConcursoRepository concursoRepository;

    public List getConcursos() {

        List concursos = new ArrayList<>();
        concursoRepository.findAll().forEach(concurso -> concursos.add(concurso));
        return concursos;

    }

    public Concurso getConcursoPorId(Long id) {

        Concurso concurso = concursoRepository.findById(id).get();

        return concurso;
    }

    

    public void salvar(Concurso concurso) {
        concursoRepository.save(concurso);
    }

    public void delete(Concurso concurso) {
        concursoRepository.delete(concurso);
    }

}
