package br.com.sistemahospitalar.repository;

import br.com.sistemahospitalar.model.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<ProfissionalSaude, Long> {
    Optional<ProfissionalSaude> findByRegistroProfissional(String registroProfissional);
}
