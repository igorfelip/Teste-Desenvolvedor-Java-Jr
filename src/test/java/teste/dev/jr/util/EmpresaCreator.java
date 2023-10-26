package teste.dev.jr.util;

import teste.dev.jr.jr.domain.Cliente;
import teste.dev.jr.jr.domain.Empresa;

public class EmpresaCreator {
    public static Empresa criaEmpresasemId() {
        return Empresa.builder().nome("Igor").build();
    }

    public static Empresa criaEmpresaComId() {
        return Empresa.builder().id(1l).nome("Igor").build();
    }

    public static Empresa criaEmpresaComNomeDiferente() {
        return Empresa.builder().id(1l).nome("José").build();
}}
