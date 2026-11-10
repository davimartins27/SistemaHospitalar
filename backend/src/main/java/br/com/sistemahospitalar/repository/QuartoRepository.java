package br.com.sistemahospitalar.repository;

import br.com.sistemahospitalar.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    Optional<Quarto> findByNumeroIdentificacao(Integer numeroIdentificacao);
}
