package teste.dev.jr.jr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teste.dev.jr.jr.domain.Cliente;
import teste.dev.jr.jr.requests.ClientePostRequestBody;
import teste.dev.jr.jr.requests.ClientePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {
    public static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    public abstract Cliente toCliente(ClientePostRequestBody clientePostRequestBody);

    public abstract Cliente toCliente(ClientePutRequestBody clientePutRequestBody);
}
