package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HospitalRepository {

    private final EntityManager em;

    public Long save(Hospital hospital) {
        em.persist(hospital);
        return hospital.getId();
    }

    public Long saveAndFlush(Hospital hospital) {
        em.persist(hospital);
        em.flush();
        return hospital.getId();
    }

    public Hospital findOne(Long id) {
        return em.find(Hospital.class, id);
    }

    public List<Hospital> findAll() {
        return em.createQuery("select h from Hospital h", Hospital.class)
                .getResultList();
    }

    public List<Hospital> findByName(String name) {
        return em.createQuery("select h from Hospital h where h.name:name", Hospital.class)
                .setParameter("name", name)
                .getResultList();
    }

}
