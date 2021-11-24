package br.com.jobem.api.repositories;

import br.com.jobem.api.models.KnowledgeArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeAreaRepository extends JpaRepository<KnowledgeArea, Integer> {
}