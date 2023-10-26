package teste.dev.jr.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import teste.dev.jr.jr.domain.Empresa;

import java.util.List;

@Repository
@Component
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Override
     List<Empresa> findAll();

    Empresa findByCnpj(Long c);


    ;
}
