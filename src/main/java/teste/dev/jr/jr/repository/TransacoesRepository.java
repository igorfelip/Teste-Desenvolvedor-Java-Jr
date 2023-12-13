package teste.dev.jr.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste.dev.jr.jr.domain.Transacoes;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {


}
