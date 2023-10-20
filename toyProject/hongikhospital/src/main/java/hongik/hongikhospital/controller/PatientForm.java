package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientForm {

    @NotEmpty(message = "필수 입력 항목")
    private String name;

    @Min(value = 1, message = "필수 입력 항목")
    private int age;

    private Gender gender;

}
