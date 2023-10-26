package teste.dev.jr.util;

import teste.dev.jr.jr.requests.ClientePostRequestBody;
import teste.dev.jr.jr.requests.EmpresaPostRequestBody;

public class EmpresaPostRequestBodyCreator {
    public static EmpresaPostRequestBody criaEmpresaSemId() {
        return EmpresaPostRequestBody.builder().nome("Igor").build();
}}
