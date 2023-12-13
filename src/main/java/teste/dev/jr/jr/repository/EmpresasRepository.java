package teste.dev.jr.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import teste.dev.jr.jr.domain.Empresas;

@Repository
@Component
public interface EmpresasRepository extends JpaRepository<Empresas, Long> {


}
