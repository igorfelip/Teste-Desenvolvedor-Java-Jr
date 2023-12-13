package teste.dev.jr.jr.requests;

import lombok.Builder;
import lombok.Data;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.domain.Empresas;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class TransacoesPutRequestBody {
    @NotNull(message = "Insira um id válido")
    private Long id;
    @NotNull(message = "Empresas inválida")
    private Empresas empresas;
    @NotNull(message = "Clientes inválido")
    private Clientes clientes;
    @DecimalMin(value = "0", message = "Insira um valor válido")
    private BigDecimal valor;
}
