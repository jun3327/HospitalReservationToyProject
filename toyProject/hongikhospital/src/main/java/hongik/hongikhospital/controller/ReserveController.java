package hongik.hongikhospital.controller;

import hongik.hongikhospital.domain.Patient;
import hongik.hongikhospital.service.PatientService;
import hongik.hongikhospital.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/hospitals/{hospitalId}/departments/{departmentId}/doctors/{doctorId}/reserve")
public class ReserveController {

    private final PatientService patientService;
    private final ReservationService reservationService;

    @GetMapping("")
    public String createReservationForm(@PathVariable("hospitalId") Long hospitalId,
                                        @PathVariable("departmentId") Long departmentId,
                                        @PathVariable("doctorId") Long doctorId,
                                        Model model) {
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patients", patients);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("hospitalId", hospitalId);
        return "reservations/createReservationsForm";
    }

    @PostMapping("")
    public String create(@PathVariable("hospitalId") Long hospitalId,
                         @PathVariable("departmentId") Long departmentId,
                         @PathVariable("doctorId") Long doctorId,
                         @RequestParam("patientId") Long patientId) {

        reservationService.createOne(hospitalId, departmentId, doctorId, patientId);

        return "redirect:/hospitals/{hospitalId}/departments";
    }
}
