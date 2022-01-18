package br.com.felipesantos.pessoaapi.repository;

import br.com.felipesantos.pessoaapi.model.Pessoa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PessoaCustomRepository {

    private final EntityManager em;

    public PessoaCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Pessoa> find(Long id, String nome, String sobrenome) {
        String query = "SELECT p FROM Pessoa p ";
        String condicao = "WHERE";

        if (id != null) {
            query += condicao + " p.id = :id";
            condicao = " AND";
        }
        if (nome != null) {
            query += condicao + " p.nome = :nome";
            condicao = " AND";
        }
        if (sobrenome != null) {
            query += condicao + " p.sobrenome = :sobrenome";
        }

        var q = em.createQuery(query, Pessoa.class);

        if (id != null) {
            q.setParameter("id", id);
        }
        if (nome != null) {
            q.setParameter("nome", nome);
        }
        if (sobrenome != null) {
            q.setParameter("sobrenome", sobrenome);
        }

        return q.getResultList() ;
    }
}
