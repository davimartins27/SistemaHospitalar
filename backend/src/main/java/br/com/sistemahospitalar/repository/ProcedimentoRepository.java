package br.com.sistemahospitalar.repository;

import br.com.sistemahospitalar.model.ProcedimentoCirurgico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedimentoRepository extends JpaRepository<ProcedimentoCirurgico, Long> {
}
