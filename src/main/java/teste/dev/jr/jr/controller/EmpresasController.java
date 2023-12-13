package teste.dev.jr.jr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teste.dev.jr.jr.domain.Empresas;
import teste.dev.jr.jr.requests.EmpresasPostRequestBody;
import teste.dev.jr.jr.requests.EmpresasPutRequestBody;
import teste.dev.jr.jr.service.EmpresasService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "empresas")
public class EmpresasController {


    private final EmpresasService empresasService;

    @GetMapping
    public ResponseEntity<List<Empresas>> findAll() {
        return new ResponseEntity<>(empresasService.findAll(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Empresas> findById(Long id) {

        return new ResponseEntity<>(empresasService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empresas> save(@RequestBody @Valid EmpresasPostRequestBody empresa) {
        return new ResponseEntity<>(empresasService.save(empresa), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid EmpresasPutRequestBody empresa) {
        empresasService.update(empresa);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

