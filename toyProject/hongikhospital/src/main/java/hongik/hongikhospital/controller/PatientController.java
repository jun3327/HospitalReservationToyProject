package hongik.hongikhospital.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PatientController {

    @GetMapping("/patients/new")
    public String createPatientForm(Model model) {
        model.addAttribute("patientForm", new PatientForm());
        return "patients/createPatientForm";
    }
}
