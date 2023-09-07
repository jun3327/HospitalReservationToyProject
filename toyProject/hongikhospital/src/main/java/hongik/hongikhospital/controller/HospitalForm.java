package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class HospitalForm {

    @NotEmpty(message = "병원 이름은 필수 항목입니다")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
