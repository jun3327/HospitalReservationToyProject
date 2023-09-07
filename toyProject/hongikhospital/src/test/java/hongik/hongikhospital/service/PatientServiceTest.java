package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Patient;
import hongik.hongikhospital.exception.DuplicatePatientException;
import hongik.hongikhospital.repository.PatientRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class PatientServiceTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Patient patient = new Patient();
        patient.setName("Kim");

        //when
        Long patientId = patientService.join(patient);

        //then
        Assertions.assertEquals(patient, patientRepository.findOne(patientId));
    }

    @Test(expected = DuplicatePatientException.class)
    public void 중복회원예외() throws Exception {
        //given
        Patient patient1 = new Patient();
        patient1.setName("Kim");

        Patient patient2 = new Patient();
        patient2.setName("Kim");

        //when
        patientService.join(patient1);
        patientService.join(patient2); //-> 예외 발생 예상
        // 단 지금은 한 트랜잭션 안에서 테스트를 하고 있고, 예외를 비즈니스 로직으로 처리하지 않고 db에 제약조건을 걸었기 떄문에,
        // PatientRepository의 save에서 em.flush()를 잠시 추가해서 테스트를 해야된다.
        // flush를 하지 않으면 위 테스트 코드는 커밋 시점에 반영되므로 이 메소드가 끝나도 예외가 터지지 않아 오류가 뜬다.

        //then
        Assertions.fail("예외가 발생해야 합니다.");
    }
}