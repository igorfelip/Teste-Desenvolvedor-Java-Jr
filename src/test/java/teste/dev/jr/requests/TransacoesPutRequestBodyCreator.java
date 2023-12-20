package teste.dev.jr.requests;


import teste.dev.jr.jr.requests.TransacoesPutRequestBody;
import teste.dev.jr.util.TransacaoCreator;

public class TransacoesPutRequestBodyCreator {
    public static TransacoesPutRequestBody criatransacoesPutRequestBody() {
        return TransacoesPutRequestBody.builder()
                .id(1L)
                .empresas(TransacaoCreator.criaTransacaoParaSerAtualizada().getEmpresas())
                .clientes(TransacaoCreator.criaTransacaoParaSerAtualizada().getClientes())
                .valor(TransacaoCreator.criaTransacaoParaSerAtualizada().getValor())
                .build();
    }
}
