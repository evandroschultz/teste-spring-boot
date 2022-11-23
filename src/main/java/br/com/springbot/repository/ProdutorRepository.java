package br.com.springbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.springbot.models.Produtor;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    
}
