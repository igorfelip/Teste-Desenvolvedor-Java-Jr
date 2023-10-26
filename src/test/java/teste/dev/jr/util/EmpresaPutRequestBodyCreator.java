package teste.dev.jr.util;

import teste.dev.jr.jr.requests.EmpresaPostRequestBody;
import teste.dev.jr.jr.requests.EmpresaPutRequestBody;

public class EmpresaPutRequestBodyCreator {
    public static EmpresaPutRequestBody criaEmpresaSemId() {
        return EmpresaPutRequestBody.builder().nome("Igor").build();
    }
}
