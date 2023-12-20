package teste.dev.jr.requests;

import teste.dev.jr.jr.requests.EmpresasPutRequestBody;
import teste.dev.jr.util.EmpresaCreator;

import java.math.BigDecimal;

public class EmpresasPutRequestBodyCreator {
    public static EmpresasPutRequestBody criaEmpresaPutRequestBody() {
        return EmpresasPutRequestBody.builder()
                .id(EmpresaCreator.criaEmpresaParaSerAtualizada().getId())
                .nome(EmpresaCreator.criaEmpresaParaSerAtualizada().getNome())
                .CNPJ(EmpresaCreator.criaEmpresaParaSerAtualizada().getCNPJ())
                .saldo(EmpresaCreator.criaEmpresaParaSerAtualizada().getSaldo())
                .taxa(EmpresaCreator.criaEmpresaParaSerAtualizada().getTaxa())
                .build();
    }
}
