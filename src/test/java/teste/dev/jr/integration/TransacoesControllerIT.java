package teste.dev.jr.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import teste.dev.jr.jr.domain.Transacoes;
import teste.dev.jr.jr.repository.ClientesRepository;
import teste.dev.jr.jr.repository.EmpresasRepository;
import teste.dev.jr.jr.repository.TransacoesRepository;
import teste.dev.jr.util.TransacaoCreator;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TransacoesControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private TransacoesRepository transacoesRepository;
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private EmpresasRepository empresasRepository;


    @Test
    void findAll() {
        empresasRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getEmpresas());
        clientesRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getClientes());
        transacoesRepository.save(TransacaoCreator.criaTransacaoValida());
        List<Transacoes> body = testRestTemplate.exchange("/transacoes", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Transacoes>>() {
                }).getBody();

        Assertions.assertThat(body).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    void save() {
        empresasRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getEmpresas());
        clientesRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getClientes());
        transacoesRepository.save(TransacaoCreator.criaTransacaoValida());
        Transacoes body = testRestTemplate.postForEntity("/transacoes", HttpMethod.POST, Transacoes.class,
                new ParameterizedTypeReference<Transacoes>() {
                }).getBody();

        Assertions.assertThat(body).isNotNull().isInstanceOf(Transacoes.class);
    }

    @Test
    void findById() {
        empresasRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getEmpresas());
        clientesRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getClientes());
        Transacoes save = transacoesRepository.save(TransacaoCreator.criaTransacaoValida());
        Long id = save.getId();
        Transacoes body = testRestTemplate.getForObject("/transacoes/{id}", Transacoes.class, id,
                new ParameterizedTypeReference<Transacoes>() {
                });
        Assertions.assertThat(body).isNotNull().isExactlyInstanceOf(Transacoes.class);
    }


    @Test
    void delete() {
        empresasRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getEmpresas());
        clientesRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getClientes());
        Transacoes save = transacoesRepository.save(TransacaoCreator.criaTransacaoValida());
        Long id = save.getId();
        ResponseEntity<Void> body = testRestTemplate.exchange("/transacoes/{id}", HttpMethod.DELETE, null,
                Void.class, id);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    void update() {
        empresasRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getEmpresas());
        clientesRepository.save(TransacaoCreator.criaTransacaoParaSerSalva().getClientes());
        Transacoes save = transacoesRepository.save(TransacaoCreator.criaTransacaoValida());
        Long id = save.getId();
        save.getClientes().setNome("Jonas");

        ResponseEntity<Void> body = testRestTemplate.exchange("/transacoes", HttpMethod.PUT, new HttpEntity<>(save),
                Void.class, id);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

