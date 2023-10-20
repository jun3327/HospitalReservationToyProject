package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.DiagnosisInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiagnosisInfoRepository {

    private final EntityManager em;

    public Long save(DiagnosisInfo diagnosisInfo) {
        em.persist(diagnosisInfo);
        return diagnosisInfo.getId();
    }

    public DiagnosisInfo findOne(Long id) {
        return em.find(DiagnosisInfo.class, id);
    }

}
