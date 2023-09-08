package hongik.hongikhospital.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentForm {

    @NotEmpty(message = "필수 입력")
    private String name;

    @NotEmpty(message = "필수 입력")
    private String phoneNumber;

}
