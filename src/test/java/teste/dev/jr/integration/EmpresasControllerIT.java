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
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.repository.EmpresasRepository;
import teste.dev.jr.util.EmpresaCreator;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmpresasControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private EmpresasRepository empresasRepository;


    @Test
    void findAll() {
        empresasRepository.save(EmpresaCreator.criaEmpresaValida());
        List<Empresas> body = testRestTemplate.exchange("/empresas", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Empresas>>() {
                }).getBody();

        Assertions.assertThat(body).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    void save() {
        empresasRepository.save(EmpresaCreator.criaEmpresaValida());
        Empresas body = testRestTemplate.postForEntity("/empresas", HttpMethod.POST, Empresas.class,
                new ParameterizedTypeReference<Empresas>() {
                }).getBody();

        Assertions.assertThat(body).isNotNull().isInstanceOf(Empresas.class);
    }

    @Test
    void findById() {
        Empresas save = empresasRepository.save(EmpresaCreator.criaEmpresaValida());
        Long id = save.getId();
        Empresas body = testRestTemplate.getForObject("/empresas/{id}", Empresas.class, id,
                new ParameterizedTypeReference<Empresas>() {
                });
        Assertions.assertThat(body).isNotNull().isExactlyInstanceOf(Empresas.class);
    }


    @Test
    void delete() {
        Empresas save = empresasRepository.save(EmpresaCreator.criaEmpresaValida());
        Long id = save.getId();
        ResponseEntity<Void> body = testRestTemplate.exchange("/empresas/{id}", HttpMethod.DELETE, null,
                Void.class, id);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    void update() {
        Empresas save = empresasRepository.save(EmpresaCreator.criaEmpresaValida());
        Long id = save.getId();
        save.setNome("Jonas");

        ResponseEntity<Void> body = testRestTemplate.exchange("/empresas", HttpMethod.PUT, new HttpEntity<>(save),
                Void.class, id);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

