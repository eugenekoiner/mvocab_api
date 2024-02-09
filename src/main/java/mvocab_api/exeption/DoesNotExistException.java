package mvocab_api.exeption;

public class DoesNotExistException extends Exception {
    public DoesNotExistException(String entry) {
        super(entry + " does not exists");
    }

    public DoesNotExistException(String entry1, String entry2) {
        super(entry1 + " or " + entry2 + " do not exist");
    }
}
