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
import teste.dev.jr.jr.domain.Empresa;
import teste.dev.jr.jr.requests.EmpresaPostRequestBody;
import teste.dev.jr.jr.service.EmpresaService;
import teste.dev.jr.util.EmpresaCreator;
import teste.dev.jr.util.EmpresaPostRequestBodyCreator;
import teste.dev.jr.util.EmpresaPutRequestBodyCreator;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class EmpresaControllerTest {
    @InjectMocks
    EmpresaController empresaController;
    @Mock
    EmpresaService empresaServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(empresaServiceMock.findAll())
                .thenReturn(List.of(EmpresaCreator.criaEmpresaComId()));
        BDDMockito.when(empresaServiceMock.save(ArgumentMatchers.any(EmpresaPostRequestBody.class)))
                .thenReturn(EmpresaCreator.criaEmpresaComId());
        BDDMockito.when(empresaServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(EmpresaCreator.criaEmpresaComId());

        BDDMockito.doNothing().when(empresaServiceMock).delete(ArgumentMatchers.anyLong());
        BDDMockito.doNothing().when(empresaServiceMock).update(ArgumentMatchers.any());
    }

    @Test
    void findAll() {
        String nome = EmpresaCreator.criaEmpresaComId().getNome();
        List<Empresa> Empresa = empresaController.findAll().getBody();
        Assertions.assertThat(nome).isEqualTo(Empresa.get(0).getNome());
        Assertions.assertThat(Empresa).isNotEmpty();
    }

    @Test
    void save() {
        Empresa Empresa = empresaController.save(EmpresaPostRequestBodyCreator.criaEmpresaSemId()).getBody();
        Assertions.assertThat(Empresa).isNotNull();

    }

    @Test
    void findById() {
        Long id = EmpresaCreator.criaEmpresaComId().getId();
        Empresa Empresa = empresaController.findById(id).getBody();
        Assertions.assertThat(Empresa.getId()).isEqualTo(id);

    }

    @Test
    void delete() {
        ResponseEntity<Void> entity = empresaController.delete(1l);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void update() {
        ResponseEntity<Void> entity = empresaController.update(EmpresaPutRequestBodyCreator.criaEmpresaSemId());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}


