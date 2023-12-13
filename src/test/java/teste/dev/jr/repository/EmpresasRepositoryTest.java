package teste.dev.jr.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.repository.EmpresasRepository;
import teste.dev.jr.util.EmpresaCreator;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class EmpresasRepositoryTest {
    @Autowired
    private EmpresasRepository empresasRepository;

    @Test
    void findAll_AchaTodasEmpresas() {
        Empresas empresa = empresasRepository.save(EmpresaCreator.criaEmpresaParaSerSalva());
        empresasRepository.save(empresa);
        List<Empresas> all = empresasRepository.findAll();

        Assertions.assertThat(all).isNotNull().isNotEmpty();
    }

    @Test
    void save_SalvaEmpresa() {
        Empresas save = empresasRepository.save(EmpresaCreator.criaEmpresaParaSerSalva());
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getId()).isNotNull();
    }

    @Test
    void findById_AchaEmpresaPeloId() {
        Empresas empresas = EmpresaCreator.criaEmpresaParaSerSalva();
        Empresas save = empresasRepository.save(empresas);
        Optional<Empresas> byId = empresasRepository.findById(save.getId());

        Assertions.assertThat(byId).isNotNull().isPresent();
    }

    @Test
    void update_AtualizaEmpresa() {
        Empresas empresas = EmpresaCreator.criaEmpresaParaSerSalva();
        Empresas save = empresasRepository.save(empresas);
        save.setNome("Ailton");
        Empresas empresaAtualizado = empresasRepository.save(save);

        Assertions.assertThat(empresaAtualizado.getNome()).isEqualTo(empresas.getNome());
    }

    @Test
    void delete_DeletaEmpresa() {
        Empresas empresas = EmpresaCreator.criaEmpresaParaSerSalva();
        Empresas save = empresasRepository.save(empresas);
        empresasRepository.delete(save);
        Optional<Empresas> byId = empresasRepository.findById(save.getId());

        Assertions.assertThat(byId).isNotPresent().isEmpty();
    }


}
