package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Address;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

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
