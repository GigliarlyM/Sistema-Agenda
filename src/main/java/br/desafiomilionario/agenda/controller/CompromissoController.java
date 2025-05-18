package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.dto.CompromissoDto;
import br.desafiomilionario.agenda.entity.Compromisso;
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
    public ResponseEntity<CompromissoDto> getOneCompromisso(@RequestParam Long id) {
        Compromisso result = service.getOne(id);
        CompromissoDto dto = new CompromissoDto(
                result.getId(),
                result.getTitulo(),
                result.getDataHora(),
                result.getLocal(),
                result.getStatus()
        );
        // Aqui nao vai da certo pois a classe Compromisso esta usando o lombok
        // E o retorno deve ser um dto (pois utilizaremos o record para isso)
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CompromissoDto> create(@RequestBody CompromissoDto body) {
        service.save(body);
        return ResponseEntity.ok().body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompromissoDto> update(@RequestParam Long id, @RequestBody CompromissoDto body) {
        service.update(id, body);
        return ResponseEntity.ok().body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompromissoDto> delete(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
