package teste.dev.jr.jr.requests;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
public class ClientesPostRequestBody {
    @NotBlank(message = "Insira o nome")
    private String nome;
    @CPF(message = "Insira o CPF")
    private String CPF;
    @DecimalMin(value = "0", message = "Insira um valor válido")
    private BigDecimal saldo;
}
