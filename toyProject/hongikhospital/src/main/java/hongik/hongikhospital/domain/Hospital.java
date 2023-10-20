package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();

    @OneToMany(mappedBy = "hospital")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();

    public static Hospital createHospital(String name, String city, String street) {
        Hospital hospital = new Hospital();
        Address address = new Address(city, street);

        hospital.name = name;
        hospital.address = address;

        return hospital;
    }
}
