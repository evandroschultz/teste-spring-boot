package br.com.springbot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.springbot.repository.EstudioRepository;
import br.com.springbot.models.Estudio;

@Service
public class EstudioService {

    @Autowired
    EstudioRepository estudioRepository;

    public List getEstudio() {

        List estudios = new ArrayList<>();
        estudioRepository.findAll().forEach(estudio -> estudios.add(estudio));
        return estudios;

    }

    public Estudio getEstudioPorId(Long id) {

        Estudio estudio = estudioRepository.findById(id).get();

        return estudio;
    }

    public void salvar(Estudio estudio) {
        estudioRepository.save(estudio);
    }

    public void delete(Estudio estudio) {
        estudioRepository.delete(estudio);
    }

}
