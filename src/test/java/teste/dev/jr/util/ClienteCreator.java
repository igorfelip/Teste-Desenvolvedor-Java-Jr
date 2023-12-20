package teste.dev.jr.util;

import teste.dev.jr.jr.domain.Clientes;

import java.math.BigDecimal;

public class ClienteCreator {
    public static Clientes criaClienteParaSerSalvo() {
        return Clientes.builder()
                .nome("Igor")
                .CPF("979.963.640-01")
                .saldo(BigDecimal.valueOf(10000))
                .build();
    }

    public static Clientes criaClienteValido() {
        return Clientes.builder()
                .id(1L)
                .CPF("979.963.640-01")
                .saldo(BigDecimal.valueOf(10000))
                .nome("Igor")
                .build();
    }

    public static Clientes criaClienteParaSerAtualizado() {
        return Clientes.builder()
                .id(1L)
                .CPF("979.963.640-01")
                .saldo(BigDecimal.valueOf(10000))
                .nome("José")
                .build();
    }
}
