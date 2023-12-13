package teste.dev.jr.util;

import teste.dev.jr.jr.domain.Transacoes;

import java.math.BigDecimal;

public class TransacaoCreator {
    public static Transacoes criaTransacaoParaSerSalva() {
        return Transacoes.builder()
                .clientes(ClienteCreator.criaClienteParaSerSalvo())
                .empresas(EmpresaCreator.criaEmpresaParaSerSalva())
                .valor(BigDecimal.valueOf(300))
                .build();
    }

    public static Transacoes criaTransacaoValida() {
        return Transacoes.builder()
                .id(1L)
                .clientes(ClienteCreator.criaClienteValido())
                .empresas(EmpresaCreator.criaEmpresaValida())
                .valor(BigDecimal.valueOf(300))
                .build();
    }

    public static Transacoes criaTransacaoParaSerAtualizada() {
        return Transacoes.builder()
                .id(1L)
                .clientes(ClienteCreator.criaClienteParaSerSalvo())
                .empresas(EmpresaCreator.criaEmpresaParaSerSalva())
                .valor(BigDecimal.valueOf(300))
                .build();
    }
}
