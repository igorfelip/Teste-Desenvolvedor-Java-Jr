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
import teste.dev.jr.jr.domain.Transacoes;
import teste.dev.jr.jr.requests.TransacoesPostRequestBody;
import teste.dev.jr.jr.service.TransacoesService;
import teste.dev.jr.requests.TransacoesPostRequestBodyCreator;
import teste.dev.jr.requests.TransacoesPutRequestBodyCreator;
import teste.dev.jr.util.TransacaoCreator;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
class TransacoesControllerTest {
    @InjectMocks
    TransacoesController transacoesController;
    @Mock
    TransacoesService transacoesServiceMock;


    @BeforeEach
    void setUp() {
        BDDMockito.when(transacoesServiceMock.findAll())
                .thenReturn(List.of(TransacaoCreator.criaTransacaoValida()));

        BDDMockito.when(transacoesServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(TransacaoCreator.criaTransacaoValida());

        BDDMockito.doNothing().when(transacoesServiceMock).delete(ArgumentMatchers.anyLong());

        BDDMockito.doNothing().when(transacoesServiceMock).update(ArgumentMatchers.any());

        BDDMockito.when(transacoesServiceMock.saque(ArgumentMatchers.any(TransacoesPostRequestBody.class), ArgumentMatchers.any()))
                .thenReturn(TransacaoCreator.criaTransacaoValida());

        BDDMockito.when(transacoesServiceMock.deposito(ArgumentMatchers.any(TransacoesPostRequestBody.class), ArgumentMatchers.any()))
                .thenReturn(TransacaoCreator.criaTransacaoValida());


    }

    @Test
    void findAll() {
        String nome = TransacaoCreator.criaTransacaoValida().getClientes().getNome();
        List<Transacoes> transacoes = transacoesController.findAll().getBody();
        Assertions.assertThat(nome).isEqualTo(transacoes.get(0).getClientes().getNome());
        Assertions.assertThat(transacoes).isNotEmpty();
    }

    @Test
    void saque() {
        Transacoes transacoes = transacoesController.saque(TransacoesPostRequestBodyCreator.criatransacoesPostRequestBody()
                , BigDecimal.valueOf(300)).getBody();
        Assertions.assertThat(transacoes).isNotNull();

    }

    @Test
    void deposito() {
        Transacoes transacoes = transacoesController.deposito(TransacoesPostRequestBodyCreator.criatransacoesPostRequestBody()
                , BigDecimal.valueOf(300)).getBody();
        Assertions.assertThat(transacoes).isNotNull();

    }

    @Test
    void findById() {
        Long id = TransacaoCreator.criaTransacaoValida().getId();
        Transacoes transacoes = transacoesController.findById(id).getBody();
        Assertions.assertThat(transacoes.getId()).isEqualTo(id);

    }

    @Test
    void delete() {
        ResponseEntity<Void> entity = transacoesController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void update() {
        ResponseEntity<Void> entity = transacoesController.update(TransacoesPutRequestBodyCreator.criatransacoesPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}