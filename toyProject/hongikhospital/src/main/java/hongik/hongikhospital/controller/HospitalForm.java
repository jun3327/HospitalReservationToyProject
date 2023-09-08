package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class HospitalForm {

    @NotEmpty(message = "필수 입력 항목")
    private String name;

    @NotEmpty(message = "필수 입력 항목")
    private String city;

    @NotEmpty(message = "필수 입력 항목")
    private String street;
}
