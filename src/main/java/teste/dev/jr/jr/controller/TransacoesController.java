package teste.dev.jr.jr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import teste.dev.jr.jr.domain.Transacoes;
import teste.dev.jr.jr.requests.TransacoesPostRequestBody;
import teste.dev.jr.jr.requests.TransacoesPutRequestBody;
import teste.dev.jr.jr.service.TransacoesService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("transacoes")
public class TransacoesController {

    private final TransacoesService transacoessService;

    @GetMapping
    public ResponseEntity<List<Transacoes>> findAll() {
        return new ResponseEntity<>(transacoessService.findAll(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Transacoes> findById(Long id) {
        return new ResponseEntity<>(transacoessService.findById(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = "/saque/{valor}")
    public ResponseEntity<Transacoes> saque(@RequestBody @Valid TransacoesPostRequestBody transacoes, @PathVariable BigDecimal valor) {
        return new ResponseEntity<>(transacoessService.saque(transacoes, valor), HttpStatus.ACCEPTED);
    }

    @Transactional
    @PostMapping(path = "/deposito/{valor}")
    public ResponseEntity<Transacoes> deposito(@RequestBody @Valid TransacoesPostRequestBody transacoes, @PathVariable BigDecimal valor) {
        return new ResponseEntity<>(transacoessService.deposito(transacoes, valor), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid TransacoesPutRequestBody transacoes) {
        transacoessService.update(transacoes);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transacoessService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
