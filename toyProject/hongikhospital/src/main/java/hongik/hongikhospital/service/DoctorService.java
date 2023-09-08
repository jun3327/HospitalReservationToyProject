package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Doctor;
import hongik.hongikhospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

//    //의사 등록
//    @Transactional
//    public Long join(Doctor doctor, Department department) {
//        validateDuplicateDoctor(doctor, department.getPhoneNumber());
//        doctorRepository.save(doctor);
//        return doctor.getId();
//    }

    private void validateDuplicateDoctor(Doctor doctor, int number) {
        List<Doctor> doctors = doctorRepository.findOneByNameAndDepartmentPhone(doctor.getName(), number);
        if ( doctors != null) {
            throw new IllegalStateException("이미 존재하는 의사 정보입니다.");
        }
    }

    //id로 한명 조회
    public Doctor findOne(Long id) {
        return doctorRepository.findOne(id);
    }

    //모든 의사 조회
    public List<Doctor> findAll() {
       return doctorRepository.findAll();
    }


    //특정 병원의 특정 진료과에 속하는 의사 조회
    public List<Doctor> findDoctorsByDepartmentId(Long id) {
        return doctorRepository.findAllByDepartmentId(id);
    }
}
