package teste.dev.jr.util;

import teste.dev.jr.jr.domain.Clientes;

import java.math.BigDecimal;

public class ClienteCreator {
    public static Clientes criaClienteParaSerSalvo() {
        return Clientes.builder()
                .nome("Igor")
                .cpf(143452L)
                .saldo(BigDecimal.valueOf(10000))
                .build();
    }

    public static Clientes criaClienteValido() {
        return Clientes.builder()
                .id(1L)
                .cpf(143452L)
                .saldo(BigDecimal.valueOf(10000))
                .nome("Igor")
                .build();
    }

    public static Clientes criaClienteParaSerAtualizado() {
        return Clientes.builder()
                .id(1L)
                .cpf(143452L)
                .saldo(BigDecimal.valueOf(10000))
                .nome("José")
                .build();
    }
}
