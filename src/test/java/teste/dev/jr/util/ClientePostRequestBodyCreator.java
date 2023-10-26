package teste.dev.jr.util;


import teste.dev.jr.jr.requests.ClientePostRequestBody;

public class ClientePostRequestBodyCreator {
    public static ClientePostRequestBody criaClienteSemId() {
        return ClientePostRequestBody.builder().nome("Igor").build();
    }
}
