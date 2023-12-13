package teste.dev.jr.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste.dev.jr.jr.domain.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {


}
