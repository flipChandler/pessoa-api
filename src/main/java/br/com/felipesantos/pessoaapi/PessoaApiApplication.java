package br.com.felipesantos.pessoaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "br.com.felipesantos.pessoaapi")
@EntityScan(basePackages = "br.com.felipesantos.pessoaapi.model" )
public class PessoaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PessoaApiApplication.class, args);
	}

}
