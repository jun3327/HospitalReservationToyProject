package hongik.hongikhospital.exception;

public class DuplicateDoctorException extends RuntimeException {
    public DuplicateDoctorException(String message) {
        super(message);
    }

    public DuplicateDoctorException(String message, Throwable cause) {
        super(message, cause);
    }
}
