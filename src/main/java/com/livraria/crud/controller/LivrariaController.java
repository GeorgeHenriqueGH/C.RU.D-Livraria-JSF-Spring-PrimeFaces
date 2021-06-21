package com.livraria.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.crud.model.Livro;
import com.livraria.crud.repositories.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivrariaController { // Classe responsável por criar endpoints e metodos do C.R.U.D
	
	@Autowired
	private LivroRepository repository;
	
    // Listar
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }
    
    // Listar por ID
    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Livro> findById(@PathVariable long id) {
        return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Cadastra novo livro
    @PostMapping
    public Livro create(@RequestBody Livro livro) {
        return repository.save(livro);
    }
    
    // Alterar objeto livro
    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") long id, @RequestBody Livro livro) {
        return repository.findById(id).map(record -> {
            record.setNome(record.getNome());
            record.setGenero(record.getGenero());
            record.setAutor(record.getAutor());
            record.setAnoLancamento(record.getAnoLancamento());
            record.setDigitalOuFisico(record.isDigitalOuFisico());
            Livro updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    // Deleta um objeto livro
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
