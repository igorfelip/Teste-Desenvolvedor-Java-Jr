package teste.dev.jr.util;

import teste.dev.jr.jr.domain.Empresas;

import java.math.BigDecimal;

public class EmpresaCreator {
    public static Empresas criaEmpresaParaSerSalva() {
        return Empresas.builder()
                .nome("Igor")
                .cnpj(41255453L)
                .saldo(BigDecimal.valueOf(0))
                .taxa(BigDecimal.valueOf(0.02))
                .build();
    }

    public static Empresas criaEmpresaValida() {
        return Empresas.builder()
                .id(1L)
                .cnpj(41255453L)
                .saldo(BigDecimal.valueOf(0))
                .taxa(BigDecimal.valueOf(0.02))
                .nome("Igor")
                .build();
    }

    public static Empresas criaEmpresaParaSerAtualizada() {
        return Empresas.builder()
                .id(1L)
                .cnpj(41255453L)
                .saldo(BigDecimal.valueOf(0))
                .taxa(BigDecimal.valueOf(0.02))
                .nome("José")
                .build();
    }
}
