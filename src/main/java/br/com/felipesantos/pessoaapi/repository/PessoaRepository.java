package br.com.felipesantos.pessoaapi.repository;

import br.com.felipesantos.pessoaapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContains(String nome);

    @Query(value = "SELECT p FROM Pessoa p WHERE p.nome = ?1")
    Pessoa buscarPessorPorNome(String nome);

}
