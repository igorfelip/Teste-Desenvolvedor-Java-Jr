package teste.dev.jr.jr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.exception.BadRequestException;
import teste.dev.jr.jr.mapper.ClientesMapper;
import teste.dev.jr.jr.repository.ClientesRepository;
import teste.dev.jr.jr.requests.ClientesPostRequestBody;
import teste.dev.jr.jr.requests.ClientesPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientesService {

    private final ClientesRepository clientesRepository;

    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    public Clientes findById(Long id) {
        return clientesRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Cliente não encontrado"));
    }

    public Clientes save(ClientesPostRequestBody cliente) {
        return clientesRepository.save(ClientesMapper.INSTANCE.toCliente(cliente));
    }

    public void update(ClientesPutRequestBody clientesPutRequestBody) {
        Clientes byId = findById(clientesPutRequestBody.getId());
        Clientes saved = (ClientesMapper.INSTANCE.toCliente(clientesPutRequestBody));
        saved.setId(byId.getId());
        clientesRepository.save(saved);

    }

    public void delete(Long id) {
        clientesRepository.delete(findById(id));
    }

}
