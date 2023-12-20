package teste.dev.jr.jr.requests;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class EmpresasPutRequestBody {
    @NotNull(message = "Insira um id válido")
    private Long id;
    @NotBlank(message = "Insira o nome")
    private String nome;
    @DecimalMin(value = "0", message = "Insira um valor válido")
    private BigDecimal saldo;
    @DecimalMin(value = "0", message = "Insira um valor válido")
    private BigDecimal taxa;
    @CNPJ(message = "Insira o CNPJ")
    private String CNPJ;
}
