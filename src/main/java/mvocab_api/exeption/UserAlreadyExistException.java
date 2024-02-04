package mvocab_api.exeption;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("already exists");
    }
}
