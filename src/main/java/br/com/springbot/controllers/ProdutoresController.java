package br.com.springbot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbot.models.Produtor;
import br.com.springbot.repository.ProdutorRepository;
import br.com.springbot.service.ProdutorService;

@RestController
@RequestMapping("/produtor")
public class ProdutoresController {

    @Autowired
    ProdutorService service;

    @Autowired
    ProdutorRepository repository;

    @PostMapping("/")
    public ResponseEntity<Produtor> save(@RequestBody Produtor produtor) {

        this.repository.save(produtor);

        return new ResponseEntity(produtor, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Produtor>> listAll() {

        return new ResponseEntity(this.repository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produtor>> getById(@PathVariable("id") Long id) {

        
        try {
            //Produtor produtorFind = this.repository.findById(id).get();
            return new ResponseEntity<Optional<Produtor>>(this.repository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Optional<Produtor>>(HttpStatus.NOT_FOUND);
        }

        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Produtor>> delete(@PathVariable("id") long id) {
        try {
            Optional<Produtor> produtor = this.repository.findById(id);
            this.repository.deleteById(id);
            return new ResponseEntity<Optional<Produtor>>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Optional<Produtor>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Produtor newProdutor) {
        return this.repository.findById(id).map(produtor -> {
            produtor.setNome(newProdutor.getNome());
            Produtor produtorAtualizado = this.repository.save(produtor);
            return ResponseEntity.ok().body("Produtor atualizado.");
        }).orElse(ResponseEntity.notFound().build());
    }

}
