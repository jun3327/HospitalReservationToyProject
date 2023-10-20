package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    private String name;

    private int career;

    @OneToMany(mappedBy = "doctor")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public static Doctor create(Hospital hospital, Department department, String name, int career) {
        Doctor doctor = new Doctor();
        doctor.setHospital(hospital);
        doctor.setDepartment(department);
        doctor.name = name;
        doctor.career = career;

        return doctor;
    }
    //연관관계 편의 메서드 --> 소속 병원, 진료과 설정
    private void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getDoctors().add(this);
    }

    private void setDepartment(Department department){
        this.department = department;
        department.getDoctors().add(this);
    }
}
