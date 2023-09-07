package hongik.hongikhospital.controller;

import hongik.hongikhospital.exception.DuplicatePatientException;
import hongik.hongikhospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients/new")
    public String createPatientForm(Model model) {
        model.addAttribute("patientForm", new PatientForm());
        return "patients/createPatientForm";
    }

    @PostMapping("/patients/new")
    public String create(@Valid PatientForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "patients/createPatientForm"; //--> result에 대한 것을 html까지 끌고가서 타임리프 문법과 태그로 오류메시지를 처리한다.
            //또한 form 데이터도 가져가서 다른 input에 있는 값도 그대로 뜬다.
        }
            // 환자 생성 서비스 호출
        patientService.createOne(form.getName(), form.getAge(), form.getGender());
        return "redirect:/";
    }
}

