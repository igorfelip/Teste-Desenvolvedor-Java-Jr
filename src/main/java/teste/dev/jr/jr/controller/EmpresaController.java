package teste.dev.jr.jr.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teste.dev.jr.jr.domain.Empresa;
import teste.dev.jr.jr.requests.EmpresaPostRequestBody;
import teste.dev.jr.jr.requests.EmpresaPutRequestBody;
import teste.dev.jr.jr.service.EmpresaService;

import javax.validation.Valid;
import java.util.List;

@Controller
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@RequestMapping(path = "empresas")
public class EmpresaController {


    private final EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> findAll() {
        return ResponseEntity.ok(empresaService.findAll());

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Empresa> findById(Long id) {
        return ResponseEntity.ok(empresaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Empresa> save(@RequestBody @Valid EmpresaPostRequestBody empresa) {
        return new ResponseEntity<>(empresaService.save(empresa), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid EmpresaPutRequestBody empresa) {
        empresaService.update(empresa);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

