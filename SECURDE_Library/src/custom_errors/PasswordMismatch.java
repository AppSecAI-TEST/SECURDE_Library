package custom_errors;

public class PasswordMismatch extends Exception{
    public PasswordMismatch () {

    }

    public PasswordMismatch (String message) {
        super (message);
    }

    public PasswordMismatch (Throwable cause) {
        super (cause);
    }

    public PasswordMismatch (String message, Throwable cause) {
        super (message, cause);
    }
}
