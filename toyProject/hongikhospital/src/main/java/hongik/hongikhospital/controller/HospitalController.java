package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Address;
import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.repository.DepartmentRepository;
import hongik.hongikhospital.repository.HospitalRepository;
import hongik.hongikhospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalRepository hospitalRepository;
    private final HospitalService hospitalService;
    private final DepartmentRepository departmentRepository;

    @GetMapping("/hospitals/new")
    public String createHospitalForm(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());
        return "/hospitals/createHospitalForm";
    }

    @PostMapping("/hospitals/new")
    public String create(@Valid HospitalForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "hospitals/createHospitalForm";
        }

        hospitalService.createOne(form.getName(), form.getCity(),
                form.getStreet());

        return "redirect:/";
    }

    @GetMapping("/hospitals")
    public String hospitals(Model model) {
        List<Hospital> hospitals = hospitalRepository.findAll();
        model.addAttribute("hospitals", hospitals);

        return "/hospitals/hospitalsList";
    }

//   /hospitals/{id}/departments
    @GetMapping("/hospitals/{hospitalId}/departments")
    public String departments(@PathVariable("hospitalId") Long hospitalId, Model model) {
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        List<Department> departments = departmentRepository.findAllByHospitalId(hospitalId);

        model.addAttribute("hospitalName", hospital.getName());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departments", departments);

        return "/departments/home";
    }
}
