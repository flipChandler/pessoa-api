package br.com.felipesantos.pessoaapi.controller.dto;

import br.com.felipesantos.pessoaapi.model.Pessoa;

public class PessoaResponse {

    private Long id;
    private String nome;
    private String sobrenome;

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

    public static PessoaResponse of(Pessoa pessoa) {
        var response = new PessoaResponse();
        response.setId(pessoa.getId());
        response.setNome(pessoa.getNome());
        response.setSobrenome(pessoa.getSobrenome());
        return response;
    }
}
