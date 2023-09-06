package hongik.hongikhospital.Service;

import hongik.hongikhospital.domain.Patient;
import hongik.hongikhospital.exception.DuplicatePatientException;
import hongik.hongikhospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    //환자 등록
    @Transactional
    public Long join(Patient patient) {

        //patient name 필드에 unique 제약 조건을 걸고, 아래 코드는 검증 코드.
        try {
            return patientRepository.save(patient);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "이미 존재하는 환자 정보입니다.";
            throw new DuplicatePatientException(errorMessage);
        }
    }

    //환자 전체 조회
    public List<Patient> findPatients() {
         return patientRepository.findAll();
    }

    //Id로 특정 환자 조회
    public Patient findOne(Long patientId) {
        return patientRepository.findOne(patientId);
    }
}
