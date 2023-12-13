package teste.dev.jr.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.repository.ClientesRepository;
import teste.dev.jr.util.ClienteCreator;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class ClientesRepositoryTest {
    @Autowired
    private ClientesRepository clientesRepository;

    @Test
    void findAll_AchaTodosClientes() {
        Clientes cliente = clientesRepository.save(ClienteCreator.criaClienteParaSerSalvo());
        clientesRepository.save(cliente);
        List<Clientes> all = clientesRepository.findAll();

        Assertions.assertThat(all).isNotNull().isNotEmpty();
    }

    @Test
    void save_SalvaCliente() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteParaSerSalvo());
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getId()).isNotNull();
    }

    @Test
    void findById_AchaClientePeloId() {
        Clientes clientes = ClienteCreator.criaClienteParaSerSalvo();
        Clientes save = clientesRepository.save(clientes);
        Optional<Clientes> byId = clientesRepository.findById(save.getId());

        Assertions.assertThat(byId).isNotNull().isPresent();
    }

    @Test
    void update_AtualizaCliente() {
        Clientes clientes = ClienteCreator.criaClienteParaSerSalvo();
        Clientes save = clientesRepository.save(clientes);
        save.setNome("Ailton");
        Clientes clienteAtualizado = clientesRepository.save(save);

        Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(clientes.getNome());
    }

    @Test
    void delete_DeletaCliente() {
        Clientes clientes = ClienteCreator.criaClienteParaSerSalvo();
        Clientes save = clientesRepository.save(clientes);
        clientesRepository.delete(save);
        Optional<Clientes> byId = clientesRepository.findById(save.getId());

        Assertions.assertThat(byId).isNotPresent().isEmpty();
    }


}
