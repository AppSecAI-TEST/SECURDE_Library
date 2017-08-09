package custom_errors;

public class LockoutException extends Exception {
    public LockoutException () {

    }

    public LockoutException (String message) {
        super (message);
    }

    public LockoutException (Throwable cause) {
        super (cause);
    }

    public LockoutException (String message, Throwable cause) {
        super (message, cause);
    }
}
