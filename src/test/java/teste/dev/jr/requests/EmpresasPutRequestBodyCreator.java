package teste.dev.jr.requests;

import teste.dev.jr.jr.requests.EmpresasPutRequestBody;

import java.math.BigDecimal;

public class EmpresasPutRequestBodyCreator {
    public static EmpresasPutRequestBody criaEmpresaPutRequestBody() {
        return EmpresasPutRequestBody.builder()
                .id(1L)
                .nome("Igor")
                .cnpj(41255453L)
                .saldo(BigDecimal.valueOf(0))
                .taxa(BigDecimal.valueOf(0.02))
                .build();
    }
}
