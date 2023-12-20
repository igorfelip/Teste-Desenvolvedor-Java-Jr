package teste.dev.jr.requests;


import teste.dev.jr.jr.requests.ClientesPostRequestBody;
import teste.dev.jr.util.ClienteCreator;

import java.math.BigDecimal;

public class ClientesPostRequestBodyCreator {
    public static ClientesPostRequestBody criaClientePostRequestBody() {
        return ClientesPostRequestBody.builder()
                .nome(ClienteCreator.criaClienteParaSerSalvo().getNome())
                .CPF(ClienteCreator.criaClienteParaSerSalvo().getCPF())
                .saldo(ClienteCreator.criaClienteParaSerSalvo().getSaldo())
                .build();
    }
}
