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
import teste.dev.jr.jr.domain.Transacoes;
import teste.dev.jr.jr.repository.TransacoesRepository;
import teste.dev.jr.jr.service.ClientesService;
import teste.dev.jr.jr.service.EmpresasService;
import teste.dev.jr.jr.service.TransacoesService;
import teste.dev.jr.requests.TransacoesPostRequestBodyCreator;
import teste.dev.jr.requests.TransacoesPutRequestBodyCreator;
import teste.dev.jr.util.TransacaoCreator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class TansacoesServiceTest {

    @InjectMocks
    TransacoesService transacaoService;
    @Mock
    TransacoesRepository transacaoRepositoryMock;
    @Mock
    ClientesService clientesService;
    @Mock
    EmpresasService empresasService;


    @BeforeEach
    void setUp() {
        BDDMockito.when(transacaoRepositoryMock.findAll())
                .thenReturn(List.of(TransacaoCreator.criaTransacaoValida()));
        BDDMockito.when(transacaoRepositoryMock.save(ArgumentMatchers.any(Transacoes.class)))
                .thenReturn(TransacaoCreator.criaTransacaoValida());
        BDDMockito.when(transacaoRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TransacaoCreator.criaTransacaoValida()));

        BDDMockito.doNothing().when(transacaoRepositoryMock).delete(ArgumentMatchers.any(Transacoes.class));
        BDDMockito.when(transacaoRepositoryMock.save(ArgumentMatchers.any(Transacoes.class)))
                .thenReturn(TransacaoCreator.criaTransacaoValida());


    }

    @Test
    void findAll_AchaTodosTransacoes() {
        String nome = TransacaoCreator.criaTransacaoValida().getClientes().getNome();
        List<Transacoes> clientes = transacaoService.findAll();
        Assertions.assertThat(nome).isEqualTo(clientes.get(0).getClientes().getNome());
        Assertions.assertThat(clientes).isNotEmpty();
    }


    @Test
    void saque() {
        Transacoes transacoes = transacaoService.saque(TransacoesPostRequestBodyCreator.criatransacoesPostRequestBody()
                , BigDecimal.valueOf(30));
        Assertions.assertThat(transacoes).isNotNull();

    }

    @Test
    void deposito() {
        Transacoes transacoes = transacaoService.deposito(TransacoesPostRequestBodyCreator.criatransacoesPostRequestBody()
                , BigDecimal.valueOf(30));
        Assertions.assertThat(transacoes).isNotNull();

    }

    @Test
    void findById_AchaTransacaoPeloId() {
        Long id = TransacaoCreator.criaTransacaoValida().getId();
        Transacoes clientes = transacaoService.findById(id);
        Assertions.assertThat(clientes.getId()).isEqualTo(id);

    }

    @Test
    void delete_DeletaCarro() {
        Assertions.assertThatCode(() -> transacaoService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void update_AtualizaTransacao() {
        Assertions.assertThatCode(() ->
                        transacaoService.update(TransacoesPutRequestBodyCreator.criatransacoesPutRequestBody()))
                .doesNotThrowAnyException();
    }


}
