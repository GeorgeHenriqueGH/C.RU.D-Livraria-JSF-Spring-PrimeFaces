package com.livraria.crud.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.livraria.crud.model.Livro;
import com.livraria.crud.repositories.LivroRepository;

import lombok.Getter;
import lombok.Setter;

@Named(value = "livroMB")
@ViewScoped
@Getter
@Setter
public class LivroMB {

	private List<Livro> livros = new ArrayList<>();
	
	private Livro livro;
	
	@Autowired
	private LivroRepository repository;
	
	
	@PostConstruct
	public List<Livro> findAll() {
		livros = repository.findAll();
		return livros;
	}
	
    public Integer getTamanhoDaLista() {
        return livros.size();
    }

    public void setTamanhoDaLista(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }

	
}
