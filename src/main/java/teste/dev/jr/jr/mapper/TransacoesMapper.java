package teste.dev.jr.jr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teste.dev.jr.jr.domain.Transacoes;
import teste.dev.jr.jr.requests.TransacoesPostRequestBody;
import teste.dev.jr.jr.requests.TransacoesPutRequestBody;

@Mapper(componentModel = "spring")
public interface TransacoesMapper {
    TransacoesMapper INSTANCE = Mappers.getMapper(TransacoesMapper.class);

    Transacoes toTransacao(TransacoesPostRequestBody transacaoPostRequestBody);

    Transacoes toTransacao(TransacoesPutRequestBody transacaoPutRequestBody);
}
