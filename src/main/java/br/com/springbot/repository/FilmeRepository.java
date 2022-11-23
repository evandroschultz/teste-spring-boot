package br.com.springbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.springbot.models.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
    
    @Query("SELECT f FROM Filme f join fetch f.produtores p")
    List<Filme> getFilmesPorProdutor();

    


}
