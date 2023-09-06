package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PatientForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;
    private int age;
    private Gender gender;

}
