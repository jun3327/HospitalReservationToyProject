package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class PatientRepository {

    private final EntityManager em;

    public Long save(Patient patient) {
        em.persist(patient);
        return patient.getId();
    }

    public Patient find(Long id) {
       return em.find(Patient.class, id);
    }
}
