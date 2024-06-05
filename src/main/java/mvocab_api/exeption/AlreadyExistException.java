package mvocab_api.exeption;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String entry) {
        super(entry + " " + "already exists");
    }

    public AlreadyExistException(String entry1, String entry2) {
        super(entry1 + " or " + entry2 + "already exist");
    }
}
