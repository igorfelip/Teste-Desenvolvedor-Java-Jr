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
import teste.dev.jr.jr.domain.Cliente;
import teste.dev.jr.jr.requests.ClientePostRequestBody;
import teste.dev.jr.jr.service.ClienteService;
import teste.dev.jr.util.ClienteCreator;
import teste.dev.jr.util.ClientePostRequestBodyCreator;
import teste.dev.jr.util.ClientePutRequestBodyCreator;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ClienteControllerTest {
    @InjectMocks
    ClienteController clienteController;
    @Mock
    ClienteService clienteServiceMock;


    @BeforeEach
    void setUp() {
        BDDMockito.when(clienteServiceMock.findAll())
                .thenReturn(List.of(ClienteCreator.criaClienteComId()));
        BDDMockito.when(clienteServiceMock.save(ArgumentMatchers.any(ClientePostRequestBody.class)))
                .thenReturn(ClienteCreator.criaClienteComId());
        BDDMockito.when(clienteServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteCreator.criaClienteComId());

        BDDMockito.doNothing().when(clienteServiceMock).delete(ArgumentMatchers.anyLong());
        BDDMockito.doNothing().when(clienteServiceMock).update(ArgumentMatchers.any());

        // BDDMockito.when(clienteServiceMock.saque(ArgumentMatchers.())).thenCallRealMethod();
        //(EmpresaCreator.criaEmpresaComId(), ClienteCreator.valor())))
        //  BDDMockito.when(clienteServiceMock.deposito(ArgumentMatchers.any(ClientePostRequestBody.class));)
        //        .thenReturn(ClienteCreator.criaClienteComId());


    }

    @Test
    void findAll() {
        String nome = ClienteCreator.criaClienteComId().getNome();
        List<Cliente> cliente = clienteController.findAll().getBody();
        Assertions.assertThat(nome).isEqualTo(cliente.get(0).getNome());
        Assertions.assertThat(cliente).isNotEmpty();
    }

    @Test
    void save() {
        Cliente cliente = clienteController.save(ClientePostRequestBodyCreator.criaClienteSemId()).getBody();
        Assertions.assertThat(cliente).isNotNull();

    }

    @Test
    void findById() {
        Long id = ClienteCreator.criaClienteComId().getId();
        Cliente cliente = clienteController.findById(id).getBody();
        Assertions.assertThat(cliente.getId()).isEqualTo(id);

    }

    @Test
    void delete() {
        ResponseEntity<Void> entity = clienteController.delete(1l);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void update() {
        ResponseEntity<Void> entity = clienteController.update(ClientePutRequestBodyCreator.criaClienteSemId());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}