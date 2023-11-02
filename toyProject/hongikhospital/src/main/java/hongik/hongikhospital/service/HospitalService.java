package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.exception.DuplicateHospitalException;
import hongik.hongikhospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    //병원 생성
    @Transactional
    public Long createOne(String name, String city, String street) {
        Hospital hospital = Hospital.createHospital(name, city, street);

        try {
            hospitalRepository.saveAndFlush(hospital);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateHospitalException("이미 존재하는 병원 정보입니다");
        }
        return hospital.getId();
    }

    //모든 병원 조회
    public List<HospitalDto> findAll() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        List<HospitalDto> result = hospitals.stream()
                .map(h -> new HospitalDto(h))
                .collect(toList());

        return result;
    }

    //특정 병원 조회
    public HospitalDto findOne(Long hospitalId) {
        return new HospitalDto(hospitalRepository.findOne(hospitalId));
    }

}
