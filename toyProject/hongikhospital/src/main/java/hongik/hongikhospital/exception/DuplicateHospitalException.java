package hongik.hongikhospital.exception;

public class DuplicateHospitalException extends RuntimeException{
    public DuplicateHospitalException(String message) {
        super(message);
    }

    public DuplicateHospitalException(String message, Throwable cause) {
        super(message, cause);
    }
}
