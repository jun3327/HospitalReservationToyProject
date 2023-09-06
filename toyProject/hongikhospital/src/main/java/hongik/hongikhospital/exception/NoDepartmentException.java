package hongik.hongikhospital.exception;

public class NoDepartmentException extends RuntimeException {
    public NoDepartmentException(String message) {
        super(message);
    }

    public NoDepartmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
