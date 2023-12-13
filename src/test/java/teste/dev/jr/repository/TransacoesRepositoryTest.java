package teste.dev.jr.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.domain.Transacoes;
import teste.dev.jr.jr.repository.ClientesRepository;
import teste.dev.jr.jr.repository.EmpresasRepository;
import teste.dev.jr.jr.repository.TransacoesRepository;
import teste.dev.jr.util.TransacaoCreator;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class TransacoesRepositoryTest {
    @Autowired
    private TransacoesRepository transacoesRepository;
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private EmpresasRepository empresasRepository;

    @Test
    void findAll_AchaTodasTransacoes() {
        Transacoes transacoes = TransacaoCreator.criaTransacaoValida();
        Clientes clientes = transacoes.getClientes();
        Empresas empresas = transacoes.getEmpresas();
        empresasRepository.save(empresas);
        clientesRepository.save(clientes);
        transacoesRepository.save(transacoes);
        List<Transacoes> all = transacoesRepository.findAll();

        Assertions.assertThat(all).isNotNull().isNotEmpty();
    }

    @Test
    void save_SalvaTransacao() {
        Transacoes transacoes = TransacaoCreator.criaTransacaoParaSerSalva();
        Transacoes save = transacoesRepository.save(transacoes);
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getId()).isNotNull();
        Assertions.assertThat(save.getClientes().getNome()).isEqualTo(transacoes.getClientes().getNome());
    }

    @Test
    void findById_AchaTransacaoPeloId() {
        Transacoes transacoes = TransacaoCreator.criaTransacaoParaSerSalva();
        Transacoes save = transacoesRepository.save(transacoes);
        Optional<Transacoes> byId = transacoesRepository.findById(save.getId());

        Assertions.assertThat(byId).isNotNull().isPresent();
    }

    @Test
    void update_AtualizaTransacao() {
        Transacoes transacoes = TransacaoCreator.criaTransacaoParaSerSalva();
        Transacoes save = transacoesRepository.save(transacoes);
        save.getClientes().setNome("Ailton");
        Transacoes transacaoAtualizado = transacoesRepository.save(save);

        Assertions.assertThat(transacaoAtualizado.getClientes().getNome()).isEqualTo(transacoes.getClientes().getNome());
    }

    @Test
    void delete_DeletaTransacao() {
        Transacoes transacoes = TransacaoCreator.criaTransacaoParaSerSalva();
        Transacoes save = transacoesRepository.save(transacoes);
        transacoesRepository.delete(save);
        Optional<Transacoes> byId = transacoesRepository.findById(save.getId());

        Assertions.assertThat(byId).isNotPresent().isEmpty();
    }


}
