package custom_errors;

public class ExpireException  extends Exception {
    public ExpireException () {

    }

    public ExpireException (String message) {
        super (message);
    }

    public ExpireException (Throwable cause) {
        super (cause);
    }

    public ExpireException (String message, Throwable cause) {
        super (message, cause);
    }
}
