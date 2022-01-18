package br.com.felipesantos.pessoaapi.controller;

import br.com.felipesantos.pessoaapi.controller.dto.PessoaRequest;
import br.com.felipesantos.pessoaapi.controller.dto.PessoaResponse;
import br.com.felipesantos.pessoaapi.model.Pessoa;
import br.com.felipesantos.pessoaapi.repository.PessoaCustomRepository;
import br.com.felipesantos.pessoaapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaCustomRepository pessoaCustomRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PessoaResponse> findAll() {
        var pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(PessoaResponse::of)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PessoaResponse findById(@PathVariable Long id) {
        var pessoa = pessoaRepository.getOne(id);
        return PessoaResponse.of(pessoa);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody PessoaRequest request) {
        pessoaRepository.save(Pessoa.of(request));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long id, @RequestBody PessoaRequest request) throws Exception {
        var optional = pessoaRepository.findById(id);
        if (optional.isPresent()) {
            var pessoa = optional.get();
            pessoa.setNome(request.getNome());
            pessoa.setSobrenome(request.getSobrenome());
            pessoaRepository.save(pessoa);
        } else {
            throw new Exception("Pessoa não encontrada!");
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PessoaResponse> findPersonByName(@RequestParam("nome") String nome) {
        return pessoaRepository.findByNomeContains(nome).stream()
                .map(PessoaResponse::of)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/filter/custom", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PessoaResponse> findPersonByCustom(@RequestParam(value = "id", required = false) Long id,
                                                   @RequestParam(value = "nome", required = false) String nome,
                                                   @RequestParam(value = "sobrenome", required = false) String sobrenome) {
        return pessoaCustomRepository.find(id, nome, sobrenome).stream()
                .map(PessoaResponse::of)
                .collect(Collectors.toList());
    }
}
