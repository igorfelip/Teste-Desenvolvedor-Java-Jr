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
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.repository.EmpresasRepository;
import teste.dev.jr.jr.service.EmpresasService;
import teste.dev.jr.requests.EmpresasPostRequestBodyCreator;
import teste.dev.jr.requests.EmpresasPutRequestBodyCreator;
import teste.dev.jr.util.EmpresaCreator;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class EmpresaServiceTest {

    @InjectMocks
    EmpresasService empresasService;
    @Mock
    EmpresasRepository empresasRepositoryMock;


    @BeforeEach
    void setUp() {
        BDDMockito.when(empresasRepositoryMock.findAll())
                .thenReturn(List.of(EmpresaCreator.criaEmpresaValida()));
        BDDMockito.when(empresasRepositoryMock.save(ArgumentMatchers.any(Empresas.class)))
                .thenReturn(EmpresaCreator.criaEmpresaValida());
        BDDMockito.when(empresasRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(EmpresaCreator.criaEmpresaValida()));

        BDDMockito.doNothing().when(empresasRepositoryMock).delete(ArgumentMatchers.any(Empresas.class));


    }

    @Test
    void findAll_AchaTodosEmpresas() {
        String nome = EmpresaCreator.criaEmpresaValida().getNome();
        List<Empresas> clientes = empresasService.findAll();
        Assertions.assertThat(nome).isEqualTo(clientes.get(0).getNome());
        Assertions.assertThat(clientes).isNotEmpty();
    }

    @Test
    void save_SalvaEmpresa() {
        Empresas clientes = empresasService.save(EmpresasPostRequestBodyCreator.criaEmpresaSemId());
        Assertions.assertThat(clientes).isNotNull();

    }

    @Test
    void findById_AchaEmpresaPeloId() {
        Long id = EmpresaCreator.criaEmpresaValida().getId();
        Empresas clientes = empresasService.findById(id);
        Assertions.assertThat(clientes.getId()).isEqualTo(id);

    }

    @Test
    void delete_DeletaCarro() {
        Assertions.assertThatCode(() -> empresasService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void update_AtualizaEmpresa() {
        Assertions.assertThatCode(() ->
                        empresasService.update(EmpresasPutRequestBodyCreator.criaEmpresaPutRequestBody()))
                .doesNotThrowAnyException();
    }


}
