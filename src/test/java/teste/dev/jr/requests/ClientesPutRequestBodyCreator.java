package teste.dev.jr.requests;

import teste.dev.jr.jr.requests.ClientesPutRequestBody;
import teste.dev.jr.util.ClienteCreator;

import java.math.BigDecimal;

public class ClientesPutRequestBodyCreator {
    public static ClientesPutRequestBody criaClientePutRequestBody() {
        return ClientesPutRequestBody.builder()
                .id(ClienteCreator.criaClienteParaSerAtualizado().getId())
                .CPF(ClienteCreator.criaClienteParaSerAtualizado().getCPF())
                .saldo(ClienteCreator.criaClienteParaSerAtualizado().getSaldo())
                .nome(ClienteCreator.criaClienteParaSerAtualizado().getNome())
                .build();
    }
}

