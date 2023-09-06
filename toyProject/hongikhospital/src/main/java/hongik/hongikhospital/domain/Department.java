package hongik.hongikhospital.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Department {

    @Id
    @Column(name = "department_name")
    private String name;

    @Column(unique = true)
    private int phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();

    //**연관관계 편의 메서드** --> 소속 병원 설정

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getDepartments().add(this);
    }
}
