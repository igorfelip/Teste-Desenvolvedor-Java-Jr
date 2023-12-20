package teste.dev.jr.requests;

import teste.dev.jr.jr.requests.EmpresasPostRequestBody;
import teste.dev.jr.util.EmpresaCreator;

import java.math.BigDecimal;

public class EmpresasPostRequestBodyCreator {
    public static EmpresasPostRequestBody criaEmpresaSemId() {
        return EmpresasPostRequestBody.builder()
                .nome(EmpresaCreator.criaEmpresaParaSerSalva().getNome())
                .CNPJ(EmpresaCreator.criaEmpresaParaSerSalva().getCNPJ())
                .saldo(EmpresaCreator.criaEmpresaParaSerSalva().getSaldo())
                .taxa(EmpresaCreator.criaEmpresaParaSerSalva().getTaxa())
                .build();
    }
}
