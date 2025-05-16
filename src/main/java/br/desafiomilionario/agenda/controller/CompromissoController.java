package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.dto.CompromissoDto;
import br.desafiomilionario.agenda.entity.Compromisso;
import br.desafiomilionario.agenda.service.CompromissoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compromisso")
public class CompromissoController {
    private final CompromissoService service;

    public CompromissoController(CompromissoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compromisso> getOneCompromisso(@RequestParam Long id) {
        Compromisso result = service.getOne(id);
        // Aqui nao vai da certo pois a classe Compromisso esta usando o lombok
        // E o retorno deve ser um dto (pois utilizaremos o record para isso)
        return ResponseEntity.ok().body(result);
    }
}
