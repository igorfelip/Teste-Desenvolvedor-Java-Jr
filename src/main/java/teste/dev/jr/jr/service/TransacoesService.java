package teste.dev.jr.jr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.domain.Transacoes;
import teste.dev.jr.jr.exception.BadRequestException;
import teste.dev.jr.jr.mapper.TransacoesMapper;
import teste.dev.jr.jr.repository.TransacoesRepository;
import teste.dev.jr.jr.requests.TransacoesPostRequestBody;
import teste.dev.jr.jr.requests.TransacoesPutRequestBody;

import java.math.BigDecimal;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransacoesService {

    private final TransacoesRepository transacoesRepository;
    private final ClientesService clientesService;
    private final EmpresasService empresasService;


    public List<Transacoes> findAll() {
        return transacoesRepository.findAll();
    }

    public Transacoes findById(Long id) {
        return transacoesRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Transacao não encontrada"));
    }

    public void update(TransacoesPutRequestBody transacoesPutRequestBody) {
        Transacoes byId = findById(transacoesPutRequestBody.getId());
        Transacoes saved = TransacoesMapper.INSTANCE.toTransacao(transacoesPutRequestBody);
        saved.setId(byId.getId());
        transacoesRepository.save(saved);

    }

    public void delete(Long id) {
        transacoesRepository.delete(findById(id));
    }


    @Transactional
    public Transacoes saque(TransacoesPostRequestBody transacao, BigDecimal valor) {
        Clientes cliente = clientesService.findById(transacao.getClientes().getId());
        Empresas empresa = empresasService.findById(transacao.getEmpresas().getId());
        if (cliente.getSaldo().compareTo(valor) < 0) {
            throw new BadRequestException("Saldo insuficiente");
        }
        empresa.setSaldo(empresa.getSaldo().subtract(valor));
        BigDecimal valorTaxa = valor.multiply(empresa.getTaxa());
        BigDecimal subtract = valor.subtract(valorTaxa);
        cliente.setSaldo(cliente.getSaldo().add(subtract));
        log.info("Saque efetuado com sucesso.");

        return transacoesRepository.save(TransacoesMapper.INSTANCE.toTransacao(transacao));

    }

    @Transactional
    public Transacoes deposito(TransacoesPostRequestBody transacao, BigDecimal valor) {
        Clientes cliente = clientesService.findById(transacao.getClientes().getId());
        Empresas empresa = empresasService.findById(transacao.getEmpresas().getId());

        if (cliente.getSaldo().compareTo(valor) < 0) {
            throw new BadRequestException("Saldo insuficiente");
        }

        cliente.setSaldo(cliente.getSaldo().subtract(valor));

        BigDecimal valorTaxa = valor.multiply(empresa.getTaxa());
        BigDecimal subtract = valor.subtract(valorTaxa);
        empresa.setSaldo(empresa.getSaldo().add(subtract));


        log.info("Deposito efetuado com sucesso.");

        return transacoesRepository.save(TransacoesMapper.INSTANCE.toTransacao(transacao));


    }

}
