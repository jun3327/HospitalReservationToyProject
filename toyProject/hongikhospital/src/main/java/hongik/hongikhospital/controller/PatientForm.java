package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PatientForm {

    @NotEmpty(message = "필수 입력 항목")
    private String name;

    @Min(value = 1, message = "필수 입력 항목")
    private int age;

    private Gender gender;

}
