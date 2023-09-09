package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {
    private final EntityManager em;

    public Long save(Doctor doctor) {
        em.persist(doctor);
        return doctor.getId();
    }

    public Long saveAndFlush(Doctor doctor) {
        em.persist(doctor);
        em.flush();

        return doctor.getId();
    }

    public Doctor findOne(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }

    //특정 진료과에 해당하는 의사 목록 조회
    public List<Doctor> findAllByDepartmentId(Long departmentId) {
        return em.createQuery("select d from Doctor d where d.department.id= :departmentId", Doctor.class)
                .setParameter("departmentId", departmentId)
                .getResultList();
    }


    //이름, 진료과 전화번호로 특정 의사 조회 --> 의사 등록 시 검증 절차에 필요,
    public List<Doctor>  findOneByNameAndDepartmentPhone(String name, int number) {
        return em.createQuery("select d from Doctor d where name= :name and department.phoneNumber= : number", Doctor.class)
                .setParameter("name", name)
                .setParameter("number", number)
                .getResultList();
    }
}
