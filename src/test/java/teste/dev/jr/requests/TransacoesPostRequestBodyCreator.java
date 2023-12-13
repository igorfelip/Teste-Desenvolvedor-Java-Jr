package teste.dev.jr.requests;


import teste.dev.jr.jr.requests.TransacoesPostRequestBody;
import teste.dev.jr.util.ClienteCreator;
import teste.dev.jr.util.EmpresaCreator;

import java.math.BigDecimal;

public class TransacoesPostRequestBodyCreator {
    public static TransacoesPostRequestBody criatransacoesPostRequestBody() {
        return TransacoesPostRequestBody.builder()
                .empresas(EmpresaCreator.criaEmpresaValida())
                .clientes(ClienteCreator.criaClienteValido())
                .valor(BigDecimal.valueOf(300))
                .build();
    }
}
