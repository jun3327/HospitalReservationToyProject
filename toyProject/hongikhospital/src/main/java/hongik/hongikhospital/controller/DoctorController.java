package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Doctor;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.service.DepartmentService;
import hongik.hongikhospital.service.DoctorService;
import hongik.hongikhospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/hospitals/{hospitalId}/departments/{departmentId}/doctors")

public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("")
    public String doctors(@PathVariable("hospitalId") Long hospitalId,
                          @PathVariable("departmentId") Long departmentId,
                          Model model) {
        List<Doctor> doctors = doctorService.findDoctorsByDepartmentId(departmentId);
        model.addAttribute("doctors", doctors);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentId", departmentId);

        return "doctors/doctorsList";
    }
    @GetMapping("new")
    public String createDoctorForm(@PathVariable("hospitalId") Long hospitalId,
                                   @PathVariable("departmentId") Long departmentId,
                                   Model model) {
        model.addAttribute("doctorForm", new DoctorForm());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentId", departmentId);
        return "doctors/createDoctorForm";
    }

    @PostMapping("new")
    public String create(@PathVariable("hospitalId") Long hospitalId,
                         @PathVariable("departmentId") Long departmentId,
                         @Valid DoctorForm form,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "doctors/createDoctorForm";
        }

        doctorService.createOne(hospitalId, departmentId, form.getName(), form.getCareer());

        return "redirect:/hospitals/{hospitalId}/departments";
    }
}
