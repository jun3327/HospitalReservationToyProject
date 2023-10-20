package hongik.hongikhospital.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class DiagnosisInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosisInfo_id", nullable = true)
    private Long id;

    private int patientExpense;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    Reservation reservation;
}
