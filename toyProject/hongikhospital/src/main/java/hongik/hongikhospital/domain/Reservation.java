package hongik.hongikhospital.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY)
    private DiagnosisInfo diagnosisInfo;

    private LocalDateTime reserveTime;

    private ReserveStatus reserveStatus; // RESERVE, CANCEL, TREAT

    //**연관관계 편의 메서드**

    public void setPatient(Patient patient) {
        this.patient = patient;
        patient.getReservations().add(this);
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getReservations().add(this);
    }

    public void setDepartment(Department department) {
        this.department = department;
//        department.getReservations().add(this); -->진료과에서 예약정보 조회를 굳이 할 필요가 없을듯
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getReservations().add(this);
    }

    public void setDiagnosisInfo(DiagnosisInfo diagnosisInfo) {
        this.diagnosisInfo = diagnosisInfo;
        diagnosisInfo.setReservation(this);
    }

    //**비즈니스 로직**

    /**
     * 예약 생성
     */
    public static Reservation createReservation(Hospital hospital, Department department,
                                                Doctor doctor, Patient patient) {

        Reservation reservation = new Reservation();

        reservation.setReserveTime(LocalDateTime.now());
        reservation.setDoctor(doctor);
        reservation.setHospital(hospital);
        reservation.setDepartment(department);
        reservation.setPatient(patient);
        reservation.setReserveStatus(ReserveStatus.RESERVE);

        return reservation;

    }
}
