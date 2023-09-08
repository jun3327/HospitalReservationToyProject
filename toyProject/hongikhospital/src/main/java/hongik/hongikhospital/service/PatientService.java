package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Gender;
import hongik.hongikhospital.domain.Patient;
import hongik.hongikhospital.exception.DuplicatePatientException;
import hongik.hongikhospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    //환자 생성, 중복 검증
    @Transactional
    public Long createOne(String name, int age, Gender gender) {
        Patient patient = Patient.createPatient(name, age, gender);

        try {
            patientRepository.saveAndFlush(patient);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatePatientException("이미 존재하는 환자 정보입니다");
        }

        return patient.getId();
    }

    //환자 전체 조회a
    public List<Patient> findPatients() {
         return patientRepository.findAll();
    }

    //Id로 특정 환자 조회
    public Patient findOne(Long patientId) {
        return patientRepository.findOne(patientId);
    }
}
