package teste.dev.jr.requests;

import teste.dev.jr.jr.requests.EmpresasPostRequestBody;

import java.math.BigDecimal;

public class EmpresasPostRequestBodyCreator {
    public static EmpresasPostRequestBody criaEmpresaSemId() {
        return EmpresasPostRequestBody.builder()
                .nome("Igor")
                .cnpj(41255453L)
                .saldo(BigDecimal.valueOf(0))
                .taxa(BigDecimal.valueOf(0.02))
                .build();
    }
}
