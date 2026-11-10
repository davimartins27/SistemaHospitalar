package br.com.sistemahospitalar.repository;

import br.com.sistemahospitalar.model.Internacao;
import br.com.sistemahospitalar.model.enums.SituacaoInternacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternacaoRepository extends JpaRepository<Internacao, Long> {
    long countByQuarto_IdAndSituacao(Long quartoId, SituacaoInternacao situacao);
}
