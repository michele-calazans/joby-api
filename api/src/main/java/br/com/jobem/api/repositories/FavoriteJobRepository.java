package br.com.jobem.api.repositories;

import br.com.jobem.api.dtos.responses.StudentApplicationDTO;
import br.com.jobem.api.dtos.responses.StudentDTO;
import br.com.jobem.api.models.FavoriteJob;
import br.com.jobem.api.models.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteJobRepository extends JpaRepository<FavoriteJob, Integer> {
    List<FavoriteJob> findByStudentId(int id);

    List<FavoriteJob> findByStudentIdOrderByIdDesc(int id);

    Optional<FavoriteJob> findByStudentIdAndJobId(int studentId, int jobId);

    List<FavoriteJob> findByJobId(int id);

//    @Query("SELECT s.student, s.job FROM favorite_job s WHERE s.job.id = ?1")
//    List<FavoriteJob> findStudentsByJob(Integer id);

    @Query("SELECT s.student FROM favorite_job s WHERE s.job.id = ?1")
    List<Student> findStudentsByJob(Integer id);

    @Query("SELECT s FROM favorite_job s WHERE s.student.id = ?1")
    List<FavoriteJob> findByStudentIdAndMatchIsTrue(int id);

    boolean existsFavoriteJobByStudentId(int id);

    Integer countByStudentId(int id);

//    @Query("SELECT NEW br.com.jobem.api.dtos.responses.StudentApplicationDTO(s.id, s.name, ci.name, i.imageContent, fj.job.id) " +
//            "FROM favorite_job fj " +
//            "INNER JOIN Student s ON fj.student.id = s.id " +
//            "INNER JOIN student_institute si ON si.id = s.studentInstitute.id " +
//            "INNER JOIN course_institute ci ON ci.id = si.courseInstitute.id " +
//            "INNER JOIN Information i ON i.id = s.information.id " +
//            "WHERE fj.job.id = ?1")
//    List<StudentApplicationDTO> listStudentApplicationDto(Integer id);

    @Query("SELECT NEW br.com.jobem.api.dtos.responses.StudentApplicationDTO(s.id, s.name, ci.name, fj.job.id) " +
            "FROM favorite_job fj " +
            "INNER JOIN Student s ON fj.student.id = s.id " +
            "INNER JOIN student_institute si ON si.id = s.studentInstitute.id " +
            "INNER JOIN course_institute ci ON ci.id = si.courseInstitute.id " +
            "WHERE fj.job.id = ?1")
    List<StudentApplicationDTO> listStudentApplicationDto(Integer id);

    @Query("SELECT NEW br.com.jobem.api.dtos.responses.StudentDTO(s.id, i.district, i.city,  tu.email, s.birthDate, ints.name, ci.dateStartConclusion, i.imageContent) " +
            "FROM favorite_job fj " +
            "LEFT JOIN Student s ON fj.student.id = s.id " +
            "LEFT JOIN student_institute si ON si.id = s.studentInstitute.id " +
            "LEFT JOIN course_institute ci ON ci.id = si.courseInstitute.id " +
            "LEFT JOIN Information i ON i.id = s.information.id " +
            "LEFT JOIN tbuser tu ON tu.id = i.user.id " +
            "LEFT JOIN Institute ints ON ints.id = ci.institute.id " +
            "WHERE s.id = ?1")
    StudentDTO listStudentDto(Integer idStudent);
}
