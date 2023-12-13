package teste.dev.jr.jr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.requests.EmpresasPostRequestBody;
import teste.dev.jr.jr.requests.EmpresasPutRequestBody;


@Mapper(componentModel = "spring")
public interface EmpresasMapper {
    EmpresasMapper INSTANCE = Mappers.getMapper(EmpresasMapper.class);

    Empresas toEmpresa(EmpresasPostRequestBody empresaPostRequestBody);

    Empresas toEmpresa(EmpresasPutRequestBody empresasPutRequestBody);
}


