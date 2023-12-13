package teste.dev.jr.jr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.requests.ClientesPostRequestBody;
import teste.dev.jr.jr.requests.ClientesPutRequestBody;

@Mapper(componentModel = "spring")
public interface ClientesMapper {
    ClientesMapper INSTANCE = Mappers.getMapper(ClientesMapper.class);

    Clientes toCliente(ClientesPostRequestBody clientesPostRequestBody);

    Clientes toCliente(ClientesPutRequestBody clientesPutRequestBody);
}
