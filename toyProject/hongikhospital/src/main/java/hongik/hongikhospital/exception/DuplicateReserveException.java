package hongik.hongikhospital.exception;

public class DuplicateReserveException extends RuntimeException {
    public DuplicateReserveException() {
        super();
    }

    public DuplicateReserveException(String message) {
        super(message);
    }

    public DuplicateReserveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateReserveException(Throwable cause) {
        super(cause);
    }

    protected DuplicateReserveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
