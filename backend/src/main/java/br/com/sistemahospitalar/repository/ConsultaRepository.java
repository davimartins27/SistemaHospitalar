package br.com.sistemahospitalar.repository;

import br.com.sistemahospitalar.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
