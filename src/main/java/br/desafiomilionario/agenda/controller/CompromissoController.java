package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.model.dto.CompromissoDto;
import br.desafiomilionario.agenda.model.entity.Compromisso;
import br.desafiomilionario.agenda.service.CompromissoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compromisso")
public class CompromissoController {
    private final CompromissoService service;

    public CompromissoController(CompromissoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompromissoDto> getOneCompromisso(@PathVariable Long id) {
        CompromissoDto result = service.getOne(id);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public ResponseEntity<CompromissoDto> create(@RequestBody CompromissoDto body) {
        CompromissoDto dto = service.save(body);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompromissoDto> update(@PathVariable Long id, @RequestBody CompromissoDto body) {
        CompromissoDto dto = service.update(id, body);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
