package teste.dev.jr.jr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import teste.dev.jr.jr.domain.Cliente;
import teste.dev.jr.jr.exception.BadRequestException;
import teste.dev.jr.jr.mapper.ClienteMapper;
import teste.dev.jr.jr.repository.ClienteRepository;
import teste.dev.jr.jr.requests.ClientePostRequestBody;
import teste.dev.jr.jr.requests.ClientePutRequestBody;
import teste.dev.jr.jr.requests.EmpresaPostRequestBody;
import teste.dev.jr.jr.util.MensagemUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Cliente não encontrado"));
    }

    public Cliente save(ClientePostRequestBody cliente) {
        return clienteRepository.save(ClienteMapper.INSTANCE.toCliente(cliente));
    }

    public void update(ClientePutRequestBody clientePutRequestBody) {
        Cliente byCpf = findByCpf(clientePutRequestBody.getCpf());
        Cliente saved = (ClienteMapper.INSTANCE.toCliente(clientePutRequestBody));
        saved.setId(byCpf.getId());
        clienteRepository.save(saved);

    }

    public void delete(Long id) {
        clienteRepository.delete(findById(id));
    }

    public Cliente findByCpf(Long cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Transactional
    public void saque(ClientePostRequestBody cliente, EmpresaPostRequestBody empresa, Double valor) {
        if (valor > empresa.getSaldo()) {
            throw new BadRequestException("Saldo insuficiente");
        }
        empresa.setSaldo(empresa.getSaldo() - valor);
        double valorTaxa = valor * empresa.getTaxa();
        valor -= valorTaxa;
        cliente.setSaldo(cliente.getSaldo() + valor);
        MensagemUtil util = MensagemUtil.builder().mensagem("Saque efetuado com sucesso.").build();
        new RestTemplate().postForObject("webhook.site", util, String.class);
        System.out.print("Saque efetuado com sucesso.");

    }

    @Transactional
    public void deposito(ClientePostRequestBody cliente, EmpresaPostRequestBody empresa, Double valor) {
        if (valor > cliente.getSaldo()) {
            throw new BadRequestException("Saldo insuficiente");
        }

        cliente.setSaldo(cliente.getSaldo() - valor);
        double valorTaxa = valor * empresa.getTaxa();
        valor -= valorTaxa;
        empresa.setSaldo(empresa.getSaldo() + valor);
        MensagemUtil util = MensagemUtil.builder().mensagem("Deposito efetuado com sucesso.").build();
        new RestTemplate().postForObject("webhook.site", util, String.class);
        System.out.print("Deposito efetuado com sucesso.");

    }

}
