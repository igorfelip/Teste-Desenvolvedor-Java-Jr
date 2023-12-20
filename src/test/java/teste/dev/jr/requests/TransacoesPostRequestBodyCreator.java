package teste.dev.jr.requests;


import teste.dev.jr.jr.requests.TransacoesPostRequestBody;
import teste.dev.jr.util.TransacaoCreator;

public class TransacoesPostRequestBodyCreator {
    public static TransacoesPostRequestBody criatransacoesPostRequestBody() {
        return TransacoesPostRequestBody.builder()
                .empresas(TransacaoCreator.criaTransacaoParaSerSalva().getEmpresas())
                .clientes(TransacaoCreator.criaTransacaoParaSerSalva().getClientes())
                .valor(TransacaoCreator.criaTransacaoParaSerSalva().getValor())
                .build();
    }
}
