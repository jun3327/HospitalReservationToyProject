package hongik.hongikhospital.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    //FlashAttribute는 리다이렉션 후에도 데이터를 유지하고 다음 요청에서 사용할 수 있는 기능입니다.
    //FlashAttribute를 사용하면 리다이렉션한 다음 컨트롤러의 @GetMapping 메서드 또는 다음 요청에서 Model에 데이터를 자동으로 추가할 필요가 없습니다.
    @ExceptionHandler(DuplicatePatientException.class)
    public String handleDuplicatePatientException(DuplicatePatientException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "이미 동일한 환자 정보가 존재합니다.");
        return "redirect:/patients/new";
    }

    @ExceptionHandler(DuplicateHospitalException.class)
    public String handleDuplicateHospitalException(DuplicateHospitalException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "이미 동일한 병원 정보가 존재합니다.");
        return "redirect:/hospitals/new";
    }

}
