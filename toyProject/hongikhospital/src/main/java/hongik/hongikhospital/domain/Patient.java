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

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender; //MAN, WOMAN

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
