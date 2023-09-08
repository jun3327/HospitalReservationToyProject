package hongik.hongikhospital.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    //연관관계 편의 메서드 --> 소속 병원, 진료과 설정
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getDoctors().add(this);
    }

    public void setDepartment(Department department){
        this.department = department;
        department.getDoctors().add(this);
    }
}
