package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Reservation;
import hongik.hongikhospital.domain.ReserveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public Long save(Reservation reservation) {
        em.persist(reservation);
        return reservation.getId();
    }

    public Reservation findOne(Long id) {
        return em.find(Reservation.class, id);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findDuplicateReservation(Long doctorId, Long patientId, ReserveStatus status) {
        return em.createQuery("select r from Reservation r left join fetch r.diagnosisInfo where r.doctor.id= :doctorId and r.patient.id= :patientId and r.reserveStatus= :status", Reservation.class)
                .setParameter("doctorId", doctorId)
                .setParameter("patientId", patientId)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Reservation> findAllByDepartmentId(Long departmentId) {
        return em.createQuery("select r from Reservation r left join fetch r.diagnosisInfo where r.department.id= :departmentId", Reservation.class)
                .setParameter("departmentId", departmentId)
                .getResultList();
    }
}
