package br.com.springbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.springbot.models.Estudio;

@Repository
public interface EstudioRepository extends JpaRepository<Estudio, Long> {
    
}
