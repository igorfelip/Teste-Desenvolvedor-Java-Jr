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
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.repository.ClientesRepository;
import teste.dev.jr.util.ClienteCreator;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ClientesControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private ClientesRepository clientesRepository;


    @Test
    void findAll() {
        clientesRepository.save(ClienteCreator.criaClienteValido());
        List<Clientes> body = testRestTemplate.exchange("/clientes", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Clientes>>() {
                }).getBody();

        Assertions.assertThat(body).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    void save() {
        clientesRepository.save(ClienteCreator.criaClienteValido());
        Clientes body = testRestTemplate.postForEntity("/clientes", HttpMethod.POST, Clientes.class,
                new ParameterizedTypeReference<Clientes>() {
                }).getBody();

        Assertions.assertThat(body).isNotNull().isInstanceOf(Clientes.class);
    }

    @Test
    void findById() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteValido());
        Long id = save.getId();
        Clientes body = testRestTemplate.getForObject("/clientes/{id}", Clientes.class, id,
                new ParameterizedTypeReference<Clientes>() {
                });
        Assertions.assertThat(body).isNotNull().isExactlyInstanceOf(Clientes.class);
    }


    @Test
    void delete() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteValido());
        Long id = save.getId();
        ResponseEntity<Void> body = testRestTemplate.exchange("/clientes/{id}", HttpMethod.DELETE, null,
                Void.class, id);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    void update() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteValido());
        Long id = save.getId();
        save.setNome("Jonas");

        ResponseEntity<Void> body = testRestTemplate.exchange("/clientes", HttpMethod.PUT, new HttpEntity<>(save),
                Void.class, id);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

