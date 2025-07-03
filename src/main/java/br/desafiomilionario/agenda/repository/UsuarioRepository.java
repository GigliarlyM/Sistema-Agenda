package br.desafiomilionario.agenda.repository;

import br.desafiomilionario.agenda.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmailAndNome(String email, String name);
    Optional<Usuario> findByNome(String nome);
}
