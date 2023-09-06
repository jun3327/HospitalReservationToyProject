package hongik.hongikhospital.Service;

import hongik.hongikhospital.domain.*;
import hongik.hongikhospital.repository.DoctorRepository;
import hongik.hongikhospital.repository.PatientRepository;
import hongik.hongikhospital.repository.ReservationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.print.Doc;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 예약등록() throws Exception {
        //given

        Patient patient = createPatient();
        Hospital hospital = createHospital();
        Department department = createDepartment(hospital);
        Doctor doctor = createDoctor(hospital, department);

        //when
//        Long reserveId = reservationService.reserve(patient.getId(), doctor.getId());
        Reservation reservation = Reservation.createReservation(doctor, patient);

        //then
        Assertions.assertThat(reservation).isNotNull();
        Assertions.assertThat(reservation.getPatient()).isEqualTo(patient);
        Assertions.assertThat(reservation.getDoctor()).isEqualTo(doctor);
        Assertions.assertThat(reservation.getHospital()).isEqualTo(hospital);
        Assertions.assertThat(reservation.getDepartment()).isEqualTo(department);
    }

    private Doctor createDoctor(Hospital hospital, Department department) {
        Doctor doctor = new Doctor();
        doctor.setName("Park");
        doctor.setDepartment(department);
        doctor.setHospital(hospital);
        em.persist(doctor);
        return doctor;
    }

    private Department createDepartment(Hospital hospital) {
        Department department = new Department();
        department.setHospital(hospital);
        department.setName("Eye");
        em.persist(department);
        return department;
    }

    private Hospital createHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("hongik");
        em.persist(hospital);
        return hospital;
    }

    private Patient createPatient() {
        Patient patient = new Patient();
        patient.setName("Kim");
        em.persist(patient);
        return patient;
    }

}