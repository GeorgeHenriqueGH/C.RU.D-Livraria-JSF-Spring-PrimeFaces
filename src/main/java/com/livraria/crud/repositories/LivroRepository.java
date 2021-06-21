package com.livraria.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.crud.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> { // Estender operações do C.R.U.D

}
