package hongik.hongikhospital.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentForm {

    @NotEmpty(message = "필수 입력")
    private String name;

    @NotEmpty(message = "필수 입력")
    private String phoneNumber;

}
