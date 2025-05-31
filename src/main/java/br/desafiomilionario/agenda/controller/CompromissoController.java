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
        Compromisso result = service.getOne(id);
        CompromissoDto dto = new CompromissoDto(
                result.getId(),
                result.getTitulo(),
                result.getDataHora(),
                result.getLocal(),
                result.getStatus()
        );
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping()
    public ResponseEntity<CompromissoDto> create(@RequestBody CompromissoDto body) {
        Long id = service.save(body);
        CompromissoDto dto = new CompromissoDto(
                id,
                body.titulo(),
                body.dataHora(),
                body.local(),
                body.status()
        );
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompromissoDto> update(@PathVariable Long id, @RequestBody CompromissoDto body) {
        service.update(id, body);
        return ResponseEntity.ok().body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompromissoDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
