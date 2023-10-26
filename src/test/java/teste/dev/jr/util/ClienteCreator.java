package teste.dev.jr.util;

import teste.dev.jr.jr.domain.Cliente;

public class ClienteCreator {
    public static Cliente criaClienteSemId() {
        return Cliente.builder().nome("Igor").build();
    }

    public static Cliente criaClienteComId() {
        return Cliente.builder().id(1l).nome("Igor").build();
    }

    public static Cliente criaClienteComNomeDiferente() {
        return Cliente.builder().id(1l).nome("José").build();
    }
    public static Double valor(){
        return 200d;
    }
}
