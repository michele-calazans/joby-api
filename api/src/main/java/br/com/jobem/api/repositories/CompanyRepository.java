package br.com.jobem.api.repositories;

import br.com.jobem.api.models.Company;
import br.com.jobem.api.models.Student;
import br.com.jobem.api.dtos.responses.StudentReportResponse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByName(String name);

    Optional<Company> findByInformation_User_Id(int id);

    @Query("SELECT DISTINCT fj.student from favorite_job fj " +
           "WHERE fj.job.company.id = ?1")
    List<Student> findStudentByJob(Integer id);

    @Query("SELECT NEW br.com.jobem.api.dtos.responses.StudentReportResponse(" +
            "s.name, i.name, j.name, s.birthDate, s.gender, j.postDate) from " +
            "Student s, Institute i, Job j, favorite_job fj, student_institute si, course_institute ci " +
            "WHERE si.id = s.studentInstitute.id AND ci.id = si.courseInstitute.id AND i.id = ci.institute.id " +
            "AND fj.student.id = s.id AND j.id = ?1")
    List<StudentReportResponse> findStudentAndInstituteByJob(Integer id);
}
