package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class PatientRepository {

    private final EntityManager em;

    public Long save(Patient patient) {
        em.persist(patient);
        return patient.getId();
    }

    public Patient findOne(Long id) {
       return em.find(Patient.class, id);
    }

    public List<Patient> findAll() {
        return em.createQuery("select p from Patient p", Patient.class)
                .getResultList();
    }

    // :name 은 파라미터이고 setParameter에서 "" 안에는 파라미터 이름, name은 파라미터에 대입될 변수, 즉
    // String name에 해당.
    public List<Patient> findByName(String name) {
        return em.createQuery("select p from Patient p where p.name =:name", Patient.class)
                .setParameter("name", name)
                .getResultList();
    }
}
