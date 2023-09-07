package hongik.hongikhospital.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long id;

    @Column(unique = true) //-> 중복 가입 방지
    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender; //MAN, WOMAN

    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations = new ArrayList<>();

    //환자 생성 메서드
    public static Patient createPatient(String name, int age, Gender gender) {

        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);

        return patient;
    }
}
