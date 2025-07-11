package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.model.dto.CompromissoDto;
import br.desafiomilionario.agenda.service.AgendaService;
import br.desafiomilionario.agenda.service.CompromissoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/compromisso")
@CrossOrigin(origins = "http://localhost:4200")
public class CompromissoController {
    private final CompromissoService service;
    private final AgendaService agendaService;

    public CompromissoController(
            CompromissoService service,
            AgendaService agendaService
    ) {
        this.service = service;
        this.agendaService = agendaService;
    }

    @PostMapping
    public ResponseEntity<CompromissoDto> create(@RequestBody CompromissoDto body) {
        CompromissoDto dto = service.save(
                body,
                agendaService.findOneAgenda(body.agendaId())
        );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.id())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompromissoDto> getOneCompromisso(@PathVariable Long id) {
        CompromissoDto result = service.getOne(id);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompromissoDto> update(@PathVariable Long id, @RequestBody CompromissoDto body) {
        CompromissoDto dto = service.update(
                id, body, agendaService.findOneAgenda(body.agendaId())
        );
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
