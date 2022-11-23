package br.com.springbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.springbot.models.Concurso;

@Repository
public interface ConcursoRepository extends JpaRepository<Concurso, Long> {
    
}
