package teste.dev.jr.requests;


import teste.dev.jr.jr.requests.ClientesPostRequestBody;

import java.math.BigDecimal;

public class ClientesPostRequestBodyCreator {
    public static ClientesPostRequestBody criaClientePostRequestBody() {
        return ClientesPostRequestBody.builder()
                .nome("Igor")
                .cpf(143452L)
                .saldo(BigDecimal.valueOf(10000))
                .build();
    }
}
