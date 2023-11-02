package hongik.hongikhospital.service;

import hongik.hongikhospital.domain.Address;
import hongik.hongikhospital.domain.Hospital;
import lombok.Data;

@Data
public class HospitalDto {

    private Long id;
    private String name;
    private Address address; //city, street

    public HospitalDto(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
    }
}
