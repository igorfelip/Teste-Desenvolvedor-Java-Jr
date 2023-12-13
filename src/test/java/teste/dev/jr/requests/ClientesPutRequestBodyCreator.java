package teste.dev.jr.requests;

import teste.dev.jr.jr.requests.ClientesPutRequestBody;

import java.math.BigDecimal;

public class ClientesPutRequestBodyCreator {
    public static ClientesPutRequestBody criaClientePutRequestBody() {
        return ClientesPutRequestBody.builder()
                .id(1L)
                .cpf(143452L)
                .saldo(BigDecimal.valueOf(10000))
                .nome("Igor")
                .build();
    }
}

