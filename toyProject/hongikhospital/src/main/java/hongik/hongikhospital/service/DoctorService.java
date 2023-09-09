package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Doctor;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.exception.DuplicateDoctorException;
import hongik.hongikhospital.repository.DepartmentRepository;
import hongik.hongikhospital.repository.DoctorRepository;
import hongik.hongikhospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    //의사 등록
    @Transactional
    public Long createOne(Long hospitalId, Long departmentId, String name, int career) {

        Hospital hospital = hospitalRepository.findOne(hospitalId);
        Department department = departmentRepository.findOne(departmentId);

        Doctor doctor = Doctor.create(hospital, department, name, career);

        try {
            doctorRepository.saveAndFlush(doctor);
        } catch(DataIntegrityViolationException e) {
            throw new DuplicateDoctorException("동일한 의사정보가 존재합니다");
        }
        return doctor.getId();
    }


    public Doctor findOne(Long id) {
        return doctorRepository.findOne(id);
    }

    public List<Doctor> findAll() {
       return doctorRepository.findAll();
    }

    //특정 병원의 특정 진료과에 속하는 의사 조회
    public List<Doctor> findDoctorsByDepartmentId(Long id) {
        return doctorRepository.findAllByDepartmentId(id);
    }
}
