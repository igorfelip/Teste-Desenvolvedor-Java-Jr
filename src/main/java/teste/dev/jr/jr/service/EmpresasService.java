package teste.dev.jr.jr.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.exception.BadRequestException;
import teste.dev.jr.jr.mapper.EmpresasMapper;
import teste.dev.jr.jr.repository.EmpresasRepository;
import teste.dev.jr.jr.requests.EmpresasPostRequestBody;
import teste.dev.jr.jr.requests.EmpresasPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresasService {
    @NonNull
    private final EmpresasRepository empresasRepository;

    public List<Empresas> findAll() {
        return empresasRepository.findAll();
    }

    public Empresas findById(Long id) {
        return empresasRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Empresa não encontrada"));
    }


    public Empresas save(EmpresasPostRequestBody empresa) {
        return empresasRepository.save(EmpresasMapper.INSTANCE.toEmpresa(empresa));
    }

    public void update(EmpresasPutRequestBody empresasPutRequestBody) {
        Empresas byId = findById(empresasPutRequestBody.getId());
        Empresas empresas1 = EmpresasMapper.INSTANCE.toEmpresa(empresasPutRequestBody);
        empresas1.setId(byId.getId());
        empresasRepository.save(empresas1);

    }

    public void delete(Long id) {
        empresasRepository.delete(findById(id));
    }

}





