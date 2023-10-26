package teste.dev.jr.jr.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teste.dev.jr.jr.domain.Empresa;
import teste.dev.jr.jr.exception.BadRequestException;
import teste.dev.jr.jr.mapper.EmpresaMapper;
import teste.dev.jr.jr.repository.EmpresaRepository;
import teste.dev.jr.jr.requests.EmpresaPostRequestBody;
import teste.dev.jr.jr.requests.EmpresaPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {
    @NonNull
    private final EmpresaRepository empresaRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) {
        return empresaRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Cliente não encontrado"));
    }


    public Empresa save(EmpresaPostRequestBody empresa) {
        return empresaRepository.save(EmpresaMapper.INSTANCE.toEmpresa(empresa));
    }

    public void update(EmpresaPutRequestBody empresaPutRequestBody) {
        Empresa byCnpj = findByCnpj(empresaPutRequestBody.getCnpj());
        Empresa empresa1 = EmpresaMapper.INSTANCE.toEmpresa(empresaPutRequestBody);
        empresa1.setId(byCnpj.getId());
        empresaRepository.save(empresa1);

    }

    public void delete(Long id) {
        empresaRepository.delete(findById(id));
    }

    public Empresa findByCnpj(Long cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }


}





