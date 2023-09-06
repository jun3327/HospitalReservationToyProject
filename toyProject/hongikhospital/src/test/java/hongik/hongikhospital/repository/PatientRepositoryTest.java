package hongik.hongikhospital.repository;

import hongik.hongikhospital.domain.Gender;
import hongik.hongikhospital.domain.Patient;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @Test
    @Transactional
    public void testPatient() {
        Patient patient1 = new Patient();

        patient1.setName("Kim");
        patient1.setGender(Gender.MAN);

        Long id = patientRepository.save(patient1);
        Patient patient2 = patientRepository.find(id);

        Assertions.assertThat(patient2.getId()).isEqualTo(patient1.getId());
        Assertions.assertThat(patient2.getName()).isEqualTo(patient1.getName());
        Assertions.assertThat(patient2.getGender()).isEqualTo(patient1.getGender());

        Assertions.assertThat(patient2).isEqualTo(patient1);
    }
}