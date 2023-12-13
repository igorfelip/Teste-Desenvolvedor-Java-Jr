package teste.dev.jr.jr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresas empresas;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes clientes;
    private BigDecimal valor;
}
