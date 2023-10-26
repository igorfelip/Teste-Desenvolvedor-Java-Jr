package teste.dev.jr.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste.dev.jr.jr.domain.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Override
    List<Cliente> findAll();

    Cliente findByCpf(Long cpf);
}
