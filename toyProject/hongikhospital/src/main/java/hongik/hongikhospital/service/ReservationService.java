package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.*;
import hongik.hongikhospital.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long createOne(Long hospitalId, Long departmentId, Long doctorId, Long patientId) {

        //엔티티 조회
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        Department department = departmentRepository.findOne(departmentId);
        Patient patient = patientRepository.findOne(patientId);
        Doctor doctor = doctorRepository.findOne(doctorId);

        //중복 예약 방지 로직
        validateDuplicateReservation(doctor.getId(), patient.getId());

        //예약 생성
        Reservation reservation = Reservation.createReservation(hospital, department, doctor, patient);

        //예약 저장
        reservationRepository.save(reservation);

        return reservation.getId();
    }

    public List<Reservation> findAllByDepartment(Long departmentId) {
        return reservationRepository.findAllByDepartmentId(departmentId);
    }

    public void validateDuplicateReservation(Long doctorId, Long patientId) {
        ReserveStatus status = ReserveStatus.RESERVE;
        List<Reservation> duplicateReservation = reservationRepository.findDuplicateReservation(doctorId, patientId, status);
        if( !duplicateReservation.isEmpty() ) {
            throw new IllegalStateException("같은 의사에게 하나의 예약만 할 수 있습니다");
        }

    }
}
