package teste.dev.jr.jr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teste.dev.jr.jr.domain.Clientes;
import teste.dev.jr.jr.requests.ClientesPostRequestBody;
import teste.dev.jr.jr.requests.ClientesPutRequestBody;
import teste.dev.jr.jr.service.ClientesService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("clientes")
public class ClientesController {

    private final ClientesService clientesService;

    @GetMapping
    public ResponseEntity<List<Clientes>> findAll() {
        return new ResponseEntity<>(clientesService.findAll(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Clientes> findById(Long id) {

        return new ResponseEntity<>(clientesService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Clientes> save(@RequestBody @Valid ClientesPostRequestBody cliente) {
        return new ResponseEntity<>(clientesService.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ClientesPutRequestBody cliente) {
        clientesService.update(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
