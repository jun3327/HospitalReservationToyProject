package hongik.hongikhospital.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DiagnosisInfo {

    @Id
    @GeneratedValue
    @Column(name = "diagnosisInfo_id", nullable = true)
    private Long id;

    private int patientExpense;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
