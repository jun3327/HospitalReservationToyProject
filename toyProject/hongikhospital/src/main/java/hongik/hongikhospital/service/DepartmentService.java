package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.exception.DuplicateDepartmentException;
import hongik.hongikhospital.exception.NoDepartmentException;
import hongik.hongikhospital.repository.DepartmentRepository;
import hongik.hongikhospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    @Transactional
    public String createOne(String name, String phoneNumber, Long hospitalId) {

        Hospital hospital = hospitalRepository.findOne(hospitalId);
        Department department = Department.create(name, phoneNumber, hospital);

        try {
            departmentRepository.saveAndFlush(department);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateDepartmentException("해당 진료과는 이미 존재합니다.");
        }

        return department.getName();
    }

    //특정 병원의 진료과 모두 조회
    public List<Department> findDepartmentsByHospitalId(Long hospitalId) {
        return departmentRepository.findAllByHospitalId(hospitalId);
    }

    //특정 병원의 특정 진료과 조회
    public Department findDepartmentByDepartmentIdAndHospitalId(Long departmentId, Long hospitalId) {
        try {
            return departmentRepository.findOneByDepartmentIdAndHospitalId(departmentId, hospitalId);
        } catch (NoResultException e) {
            String errorMessage = "찾으시는 진료과 정보가 존재하지 않습니다. 다시 입력해주세요.";
            throw new NoDepartmentException(errorMessage);
        }
    }

    //전화번호로 조회
    public Department findDepartmentByPhoneNumber(Long phoneNumber) {
        try {
            return departmentRepository.findOneByPhoneNumber(phoneNumber);
        } catch (NoResultException e) {
            String errorMessage = "찾으시는 진료과 정보가 존재하지 않습니다. 다시 입력해주세요.";
            throw new NoDepartmentException(errorMessage);
        }
    }
}
