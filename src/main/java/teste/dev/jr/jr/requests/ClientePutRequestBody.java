package teste.dev.jr.jr.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class ClientePutRequestBody {
    @NotEmpty(message = "Insira o nome")
    private String nome;
    @NotEmpty(message = "Insira o CPF")
    private Long cpf;
    private Double saldo;
}
