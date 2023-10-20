package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String name;

    @Column(unique = true)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();

    public static Department create(String name, String phoneNumber, Hospital hospital) {

        Department department = new Department();
        department.name = name;
        department.phoneNumber = phoneNumber;
        department.setHospital(hospital);

        return department;
    }

    //**연관관계 편의 메서드** --> 소속 병원 설정
    private void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getDepartments().add(this);
    }
}
