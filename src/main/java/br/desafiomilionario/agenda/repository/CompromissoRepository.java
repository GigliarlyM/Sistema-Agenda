package br.desafiomilionario.agenda.repository;

import br.desafiomilionario.agenda.model.entity.Compromisso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {
    List<Compromisso> findByAgenda_Id(Long id);
}
