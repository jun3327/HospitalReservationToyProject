package hongik.hongikhospital.Service;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.exception.NoDepartmentException;
import hongik.hongikhospital.repository.DepartmentRepository;
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

    // 특정 병원의 진료과 등록
    @Transactional
    public String join(Department department) {
            // 중복 검사를 수행하고 중복이 없을 경우 저장
            validateDuplicateDepartment(department);
            return departmentRepository.save(department);
    }

    private void validateDuplicateDepartment(Department department) {
        // 병원 ID와 전화번호로 중복 검사
        List<Department> departments = departmentRepository.findByHospitalIdAndPhoneNumber(department.getHospital().getId(), department.getPhoneNumber());
        if (departments != null) {
            throw new IllegalStateException("이미 존재하는 진료과입니다.");
        }
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
