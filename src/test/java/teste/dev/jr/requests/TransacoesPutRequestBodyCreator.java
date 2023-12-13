package teste.dev.jr.requests;


import teste.dev.jr.jr.requests.TransacoesPutRequestBody;
import teste.dev.jr.util.ClienteCreator;
import teste.dev.jr.util.EmpresaCreator;

import java.math.BigDecimal;

public class TransacoesPutRequestBodyCreator {
    public static TransacoesPutRequestBody criatransacoesPutRequestBody() {
        return TransacoesPutRequestBody.builder()
                .id(1L)
                .empresas(EmpresaCreator.criaEmpresaValida())
                .clientes(ClienteCreator.criaClienteValido())
                .valor(BigDecimal.valueOf(300))
                .build();
    }
}
