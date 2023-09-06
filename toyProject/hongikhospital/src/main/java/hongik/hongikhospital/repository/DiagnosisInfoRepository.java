package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.DiagnosisInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

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
