package teste.dev.jr.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.repository.ClientesRepository;
import teste.dev.jr.jr.service.ClientesService;
import teste.dev.jr.requests.ClientesPostRequestBodyCreator;
import teste.dev.jr.requests.ClientesPutRequestBodyCreator;
import teste.dev.jr.util.ClienteCreator;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ClienteServiceTest {

    @InjectMocks
    ClientesService clientesService;
    @Mock
    ClientesRepository clientesRepositoryMock;


    @BeforeEach
    void setUp() {
        BDDMockito.when(clientesRepositoryMock.findAll())
                .thenReturn(List.of(ClienteCreator.criaClienteValido()));
        BDDMockito.when(clientesRepositoryMock.save(ArgumentMatchers.any(Clientes.class)))
                .thenReturn(ClienteCreator.criaClienteValido());
        BDDMockito.when(clientesRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ClienteCreator.criaClienteValido()));

        BDDMockito.doNothing().when(clientesRepositoryMock).delete(ArgumentMatchers.any(Clientes.class));


    }

    @Test
    void findAll_AchaTodosClientes() {
        String nome = ClienteCreator.criaClienteValido().getNome();
        List<Clientes> clientes = clientesService.findAll();
        Assertions.assertThat(nome).isEqualTo(clientes.get(0).getNome());
        Assertions.assertThat(clientes).isNotEmpty();
    }

    @Test
    void save_SalvaCliente() {
        Clientes clientes = clientesService.save(ClientesPostRequestBodyCreator.criaClientePostRequestBody());
        Assertions.assertThat(clientes).isNotNull();

    }

    @Test
    void findById_AchaClientePeloId() {
        Long id = ClienteCreator.criaClienteValido().getId();
        Clientes clientes = clientesService.findById(id);
        Assertions.assertThat(clientes.getId()).isEqualTo(id);

    }

    @Test
    void delete_DeletaCarro() {
        Assertions.assertThatCode(() -> clientesService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void update_AtualizaCliente() {
        Assertions.assertThatCode(() ->
                        clientesService.update(ClientesPutRequestBodyCreator.criaClientePutRequestBody()))
                .doesNotThrowAnyException();
    }


}
