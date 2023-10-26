package teste.dev.jr.jr.mapper;

import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teste.dev.jr.jr.domain.Empresa;
import teste.dev.jr.jr.requests.EmpresaPostRequestBody;
import teste.dev.jr.jr.requests.EmpresaPutRequestBody;

@Data
@Mapper(componentModel = "spring")
public abstract class EmpresaMapper {
    public static final EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    public abstract Empresa toEmpresa(EmpresaPostRequestBody empresaPostRequestBody);

    public abstract Empresa toEmpresa(EmpresaPutRequestBody empresaPutRequestBody);
}


