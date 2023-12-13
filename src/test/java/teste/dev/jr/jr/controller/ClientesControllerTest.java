package teste.dev.jr.jr.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.requests.ClientesPostRequestBody;
import teste.dev.jr.jr.service.ClientesService;
import teste.dev.jr.requests.ClientesPostRequestBodyCreator;
import teste.dev.jr.requests.ClientesPutRequestBodyCreator;
import teste.dev.jr.util.ClienteCreator;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ClientesControllerTest {
    @InjectMocks
    ClientesController clientesController;
    @Mock
    ClientesService clientesServiceMock;


    @BeforeEach
    void setUp() {
        BDDMockito.when(clientesServiceMock.findAll())
                .thenReturn(List.of(ClienteCreator.criaClienteValido()));
        BDDMockito.when(clientesServiceMock.save(ArgumentMatchers.any(ClientesPostRequestBody.class)))
                .thenReturn(ClienteCreator.criaClienteValido());
        BDDMockito.when(clientesServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteCreator.criaClienteValido());

        BDDMockito.doNothing().when(clientesServiceMock).delete(ArgumentMatchers.anyLong());
        BDDMockito.doNothing().when(clientesServiceMock).update(ArgumentMatchers.any());

    }

    @Test
    void findAll() {
        String nome = ClienteCreator.criaClienteValido().getNome();
        List<Clientes> clientes = clientesController.findAll().getBody();
        Assertions.assertThat(nome).isEqualTo(clientes.get(0).getNome());
        Assertions.assertThat(clientes).isNotEmpty();
    }

    @Test
    void save() {
        Clientes clientes = clientesController.save(ClientesPostRequestBodyCreator.criaClientePostRequestBody()).getBody();
        Assertions.assertThat(clientes).isNotNull();

    }

    @Test
    void findById() {
        Long id = ClienteCreator.criaClienteValido().getId();
        Clientes clientes = clientesController.findById(id).getBody();
        Assertions.assertThat(clientes.getId()).isEqualTo(id);

    }

    @Test
    void delete() {
        ResponseEntity<Void> entity = clientesController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void update() {
        ResponseEntity<Void> entity = clientesController.update(ClientesPutRequestBodyCreator.criaClientePutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}