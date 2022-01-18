package br.com.felipesantos.pessoaapi.model;

import br.com.felipesantos.pessoaapi.controller.dto.PessoaRequest;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;

    public Pessoa() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public static Pessoa of(PessoaRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.getNome());
        pessoa.setSobrenome(request.getSobrenome());
        return pessoa;
    }
}
