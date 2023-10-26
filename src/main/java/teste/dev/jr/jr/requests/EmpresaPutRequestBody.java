package teste.dev.jr.jr.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class EmpresaPutRequestBody {
    @NotEmpty(message = "Insira o nome")
    private String nome;
    private Double saldo;
    @NotEmpty
    private Double taxa;
    @NotEmpty(message = "Insira o CNPJ")
    private Long cnpj;
}
