package hongik.hongikhospital.Service;

import hongik.hongikhospital.domain.DiagnosisInfo;
import hongik.hongikhospital.repository.DiagnosisInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiagnosisInfoService {

    private final DiagnosisInfoRepository diagnosisInfoRepository;

    //진료 정보 등록
    @Transactional
    public Long join(DiagnosisInfo diagnosisInfo) {
        diagnosisInfoRepository.save(diagnosisInfo);
        return diagnosisInfo.getId();
    }

    //진료 정보 단건 조회
    public DiagnosisInfo findOne(Long id) {
        return diagnosisInfoRepository.findOne(id);
    }
}
