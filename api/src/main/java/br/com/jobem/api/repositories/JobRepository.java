package br.com.jobem.api.repositories;

import br.com.jobem.api.dtos.responses.JobDTO;
import br.com.jobem.api.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByKnowledgeAreaIdAndAcademicFormationId(int idK, int idA);

    List<Job> findByCompany_Id(int id);

    int countByCompany_Id(int id);

    @Query("SELECT NEW br.com.jobem.api.dtos.responses.JobDTO(j.id, j.name) FROM Job j WHERE j.company.id = ?1")
    List<JobDTO> listJobDto(Integer id);
}