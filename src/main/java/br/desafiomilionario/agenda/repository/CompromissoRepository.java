package br.desafiomilionario.agenda.repository;

import br.desafiomilionario.agenda.entity.Compromisso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {
}
