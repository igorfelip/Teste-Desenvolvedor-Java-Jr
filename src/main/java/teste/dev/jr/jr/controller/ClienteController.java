package teste.dev.jr.jr.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teste.dev.jr.jr.domain.Cliente;
import teste.dev.jr.jr.requests.ClientePostRequestBody;
import teste.dev.jr.jr.requests.ClientePutRequestBody;
import teste.dev.jr.jr.requests.EmpresaPostRequestBody;
import teste.dev.jr.jr.service.ClienteService;

import javax.validation.Valid;
import java.util.List;

@Controller
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClientePostRequestBody cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ClientePutRequestBody cliente) {
        clienteService.update(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/saque")
    public ResponseEntity<Void> saque(@RequestParam ClientePostRequestBody cliente, EmpresaPostRequestBody empresa, Double valor) {
        clienteService.saque(cliente, empresa, valor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/saque")
    public ResponseEntity<Void> deposito(@RequestParam ClientePostRequestBody cliente, EmpresaPostRequestBody empresa, Double valor) {
        clienteService.deposito(cliente, empresa, valor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
