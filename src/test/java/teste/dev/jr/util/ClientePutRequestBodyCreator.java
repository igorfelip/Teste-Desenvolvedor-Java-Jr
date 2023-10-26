package teste.dev.jr.util;

import teste.dev.jr.jr.requests.ClientePostRequestBody;
import teste.dev.jr.jr.requests.ClientePutRequestBody;

public class ClientePutRequestBodyCreator {
    public static ClientePutRequestBody criaClienteSemId() {
        return ClientePutRequestBody.builder().nome("Igor").build();
    }
}

