package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Department;
import hongik.hongikhospital.domain.Hospital;
import hongik.hongikhospital.domain.Reservation;
import hongik.hongikhospital.service.DepartmentService;
import hongik.hongikhospital.service.HospitalService;
import hongik.hongikhospital.service.ReservationService;
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
    private final HospitalService hospitalService;
    private final ReservationService reservationService;

    @GetMapping("")
    public String departments(@PathVariable("hospitalId") Long hospitalId, Model model) {

        Hospital hospital = hospitalService.findOne(hospitalId);
        List<Department> departments = departmentService.findDepartmentsByHospitalId(hospitalId);
        model.addAttribute("hospitalName", hospital.getName());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departments", departments);

        return "/departments/departmentsList";
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

    @GetMapping("/{departmentId}/reserveList")
    public String reserveList(@PathVariable("departmentId") Long departmentId,
                              Model model) {
        List<Reservation> reservations = reservationService.findAllByDepartment(departmentId);
        model.addAttribute("reservations", reservations);
        return "/departments/reserveList";
    }
}
