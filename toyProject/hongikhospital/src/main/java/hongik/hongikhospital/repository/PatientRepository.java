package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class PatientRepository{

    private final EntityManager em;

    public Long save(Patient patient) {
        em.persist(patient);
        return patient.getId();
    }
    public Long saveAndFlush(Patient patient) {
        em.persist(patient);
        em.flush();
        return patient.getId();
    }
    public Patient findOne(Long id) {
       return em.find(Patient.class, id);
    }

    public List<Patient> findAll() {
        return em.createQuery("select p from Patient p", Patient.class)
                .getResultList();
    }

}
