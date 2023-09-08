package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.repository.DepartmentRepository;
import hongik.hongikhospital.repository.HospitalRepository;
import hongik.hongikhospital.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hospitals/{hospitalId}/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    @GetMapping("")
    public String departments(@PathVariable("hospitalId") Long hospitalId, Model model) {
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        List<Department> departments = departmentRepository.findAllByHospitalId(hospitalId);

        model.addAttribute("hospitalName", hospital.getName());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departments", departments);

        return "departments/home";
    }

    @GetMapping("/new")
    public String createDepartmentForm(@PathVariable("hospitalId") Long hospitalId, Model model) {
        model.addAttribute("departmentForm", new DepartmentForm());
        model.addAttribute("hospitalId", hospitalId);
        return "departments/createDepartmentForm";
    }

    @PostMapping("/new")
    public String create(@Valid DepartmentForm form,
                         @PathVariable("hospitalId") Long hospitalId,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "departments/createDepartmentForm";
        }

        departmentService.createOne(form.getName(), form.getPhoneNumber(), hospitalId);

        return "redirect:/hospitals/{hospitalId}/departments";
    }
}
