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
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.requests.EmpresasPostRequestBody;
import teste.dev.jr.jr.service.EmpresasService;
import teste.dev.jr.requests.EmpresasPostRequestBodyCreator;
import teste.dev.jr.requests.EmpresasPutRequestBodyCreator;
import teste.dev.jr.util.EmpresaCreator;

import java.util.List;

@ExtendWith(SpringExtension.class)
 class EmpresasControllerTest {
    @InjectMocks
    EmpresasController empresasController;
    @Mock
    EmpresasService empresasServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(empresasServiceMock.findAll())
                .thenReturn(List.of(EmpresaCreator.criaEmpresaValida()));
        BDDMockito.when(empresasServiceMock.save(ArgumentMatchers.any(EmpresasPostRequestBody.class)))
                .thenReturn(EmpresaCreator.criaEmpresaValida());
        BDDMockito.when(empresasServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(EmpresaCreator.criaEmpresaValida());

        BDDMockito.doNothing().when(empresasServiceMock).delete(ArgumentMatchers.anyLong());
        BDDMockito.doNothing().when(empresasServiceMock).update(ArgumentMatchers.any());
    }

    @Test
    void findAll() {
        String nome = EmpresaCreator.criaEmpresaValida().getNome();
        List<Empresas> Empresas = empresasController.findAll().getBody();
        Assertions.assertThat(nome).isEqualTo(Empresas.get(0).getNome());
        Assertions.assertThat(Empresas).isNotEmpty();
    }

    @Test
    void save() {
        Empresas Empresas = empresasController.save(EmpresasPostRequestBodyCreator.criaEmpresaSemId()).getBody();
        Assertions.assertThat(Empresas).isNotNull();

    }

    @Test
    void findById() {
        Long id = EmpresaCreator.criaEmpresaValida().getId();
        Empresas Empresas = empresasController.findById(id).getBody();
        Assertions.assertThat(Empresas.getId()).isEqualTo(id);

    }

    @Test
    void delete() {
        ResponseEntity<Void> entity = empresasController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void update() {
        ResponseEntity<Void> entity = empresasController.update(EmpresasPutRequestBodyCreator.criaEmpresaPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}


